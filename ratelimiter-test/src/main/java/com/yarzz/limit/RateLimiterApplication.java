package com.yarzz.limit;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 启动类
 *
 * @author Mr.Moonzzc
 * @date 2022-08-31 18:03
 **/
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class RateLimiterApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(RateLimiterApplication.class)
				.web(WebApplicationType.SERVLET)
				.run(args);
		// SpringApplication.run(RateLimiterApplication.class, args);
	}
}
