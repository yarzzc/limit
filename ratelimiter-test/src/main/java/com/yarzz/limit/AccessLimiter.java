package com.yarzz.limit;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 限流器
 *
 * @author Mr.Moonzzc
 * @date 2022-08-30 18:00
 **/
@Service
@Slf4j
public class AccessLimiter {

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Resource
	private RedisScript<Boolean> rateLimiterLua;

	public void limitAccess(String key, Integer limit) {
		// 1. 访问 Lua 脚本
		// Lua script
		// Lua script key
		// Lua script value
		boolean acquire = Boolean.TRUE.equals(stringRedisTemplate.execute(
				// Lua script
				rateLimiterLua,
				// Lua script key
				Lists.newArrayList(key),
				// Lua script value
				limit.toString()
		));

		if(!acquire) {
			log.error("访问过于频繁，请稍后再试，当前访问key：{}", key);
			throw new RuntimeException("访问频率过高");
		}
	}
}
