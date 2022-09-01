package com.yarzz.limit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import javax.annotation.Resource;

/**
 * Redis 配置类
 *
 * @author Mr.Moonzzc
 * @date 2022-08-31 17:07
 **/
@Configuration
public class RedisConfiguration {

	@Resource
	private RedisConnectionFactory redisConnectionFactory;

	@Bean
	public RedisTemplate<String, String> redisTemplate() {
		return new StringRedisTemplate(redisConnectionFactory);
	}

	@Bean
	public DefaultRedisScript loadRedisScript() {
		DefaultRedisScript redisScript = new DefaultRedisScript();
		redisScript.setLocation(new ClassPathResource("RateLimiter.lua"));
		redisScript.setResultType(Boolean.class);
		return redisScript;
	}
}
