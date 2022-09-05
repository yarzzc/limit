package com.yarzzc.limit;

import com.yarzzc.limit.annotation.AccessLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 控制器
 *
 * @author Mr.Moonzzc
 * @date 2022-09-01 14:25
 **/

@Slf4j
@RestController
@RequestMapping("/limit")
public class TestController {
	@Resource
	private AccessLimiterService accessLimiterService;
	@Resource
	private TestService testService;

	@GetMapping("test")
	public String test() {
		accessLimiterService.limitAccess("test", 1);
		return getString("success");
	}

	@GetMapping("test1")
	@AccessLimiter(windowSize = 10, sendSize = 2, windowTime = 60)
	public String test1(String name) {
		((TestController)AopContext.currentProxy()).a(name);
		((TestController)AopContext.currentProxy()).getString("success " + name);
		return getString("success " + name);
	}

	@AccessLimiter(windowSize = 10, sendSize = 3, windowTime = 60)
	public String getString(String name) {
		return name;
	}

	@AccessLimiter(windowSize = 10, sendSize = 4, windowTime = 60)
	public String a(String name) {
		return name;
	}

	@GetMapping("test2")
	@AccessLimiter(windowSize = 10, sendSize = 2, windowTime = 60)
	public String test2(String name) {
		testService.a(name);
		testService.getString(name);
		return getString("success " + name);
	}
}
