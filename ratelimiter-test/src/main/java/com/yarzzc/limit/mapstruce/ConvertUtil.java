package com.yarzzc.limit.mapstruce;

import org.mapstruct.Named;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 转换类
 *
 * @author Mr.Moonzzc
 * @date 2022-09-07 18:49
 **/
public class ConvertUtil {

	@Named("boToPo")
	public String boToPo(List<User> user) {
		if (StringUtils.isEmpty(user)) {
			return null;
		}

		return user.get(1).getUserNick();
	}
}
