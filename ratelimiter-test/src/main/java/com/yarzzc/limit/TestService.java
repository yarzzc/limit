package com.yarzzc.limit;

import com.yarzzc.limit.annotation.AccessLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 测试服务
 *
 * @author Mr.Moonzzc
 * @date 2022-09-05 10:50
 **/
@Slf4j
@Service
public class TestService {

	@AccessLimiter(windowSize = 10, sendSize = 3, windowTime = 60)
	public String getString(String name) {
		return name;
	}

	@AccessLimiter(windowSize = 10, sendSize = 4, windowTime = 60)
	public String a(String name) {
		return name;
	}
}
