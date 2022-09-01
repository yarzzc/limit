package com.yarzz.limit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 控制器
 *
 * @author Mr.Moonzzc
 * @date 2022-09-01 14:25
 **/
@RestController
@Slf4j
public class Controller {

	@Resource
	private AccessLimiter accessLimiter;

	@GetMapping("test")
	public String test(){
		accessLimiter.limitAccess("test", 1);
		return "success";
	}
}
