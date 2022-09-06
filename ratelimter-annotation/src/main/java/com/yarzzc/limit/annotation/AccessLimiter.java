package com.yarzzc.limit.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * 限流注解
 *
 * @author Mr.Moonzzc
 * @date 2022-09-02 14:37
 **/
@Target({TYPE, METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimiter {

	/**
	 * 限流窗口大小
	 */
	int windowSize() default 1;

	/**
	 * 发送窗口大小
	 */
	int sendSize() default 1;

	/**
	 * 限流窗口时间
	 */
	int windowTime() default 1;

	/**
	 * 方法签名
	 */
	String methodKey() default "";
}
