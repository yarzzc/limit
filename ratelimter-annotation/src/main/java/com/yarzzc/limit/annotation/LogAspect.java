package com.yarzzc.limit.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 日志切面
 *
 * @author yarzzc
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

	@Pointcut("execution(public * com.yarzzc.limit.*.*(..))")
	public void cut() {
	}

	@Around("cut()")
	public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {

		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		String collect = Arrays.stream(args).map(Object::toString).collect(Collectors.joining(","));
		log.info("-----> 执行开始：{}#{}，args: {}", className, methodName, collect);
		// 执行方法
		try {
			Object result = joinPoint.proceed();
			log.info("<----- 执行结束：{}#{}，result: {}", className, methodName, result);
			return result;
		} catch (Throwable throwable) {
			log.error("<---- 执行异常：{}#{}，exception: {}", className, methodName, throwable);
			throw throwable;
		}
	}
}
