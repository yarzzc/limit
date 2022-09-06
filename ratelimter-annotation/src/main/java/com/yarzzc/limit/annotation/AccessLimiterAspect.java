package com.yarzzc.limit.annotation;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 限流注解切面
 *
 * @author Mr.Moonzzc
 * @date 2022-09-02 14:40
 **/
@Slf4j
@Aspect
@Component
public class AccessLimiterAspect {
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	@Resource
	private RedisScript<Boolean> rateLimiterLua;

	@Pointcut("@annotation(com.yarzzc.limit.annotation.AccessLimiter)")
	public void cut() {
	}

	@Before("cut()")
	public void before(JoinPoint joinPoint) {
		// 1. 获得方法签名，作为 methodKey
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		AccessLimiter annotation = method.getAnnotation(AccessLimiter.class);
		if (annotation == null) {
			return;
		}

		String key = annotation.methodKey();
		int windowSize = annotation.windowSize();
		int sendSize = annotation.sendSize();
		int windowTime = annotation.windowTime();

		// 如果没有设置 methodKey，则使用类名+方法名作为 key
		if (StringUtils.isEmpty(key)) {
			Class[] type = method.getParameterTypes();
			key = method.getDeclaringClass().getName() + "." + method.getName();

			String paramTypes = Arrays.stream(type)
					.map(Class::getName)
					.collect(Collectors.joining(","));
			key += "#" + paramTypes;
		}

		// 2. 调用 Redis
		boolean acquire = Boolean.TRUE.equals(stringRedisTemplate.execute(
				// Lua script
				rateLimiterLua,
				// Lua script key
				Lists.newArrayList(key),
				// Lua script value
				Integer.toString(windowSize),
				Integer.toString(sendSize),
				Integer.toString(windowTime)
		));

		if (!acquire) {
			log.error("访问过于频繁，请稍后再试，当前访问key：{}", key);
			throw new RuntimeException("访问频率过高");
		}
	}
}
