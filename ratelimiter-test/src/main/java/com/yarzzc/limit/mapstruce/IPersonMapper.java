package com.yarzzc.limit.mapstruce;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 转换接口
 *
 * @author Mr.Moonzzc
 * @date 2022-09-07 17:49
 **/
@Mapper()
public interface IPersonMapper {
	IPersonMapper INSTANCE = Mappers.getMapper(IPersonMapper.class);

	@Mapping(target = "userNick1", source = "userNick", defaultValue = "1")
	UserEntity po2entity(UserPo userPo);


	@Mapping(target = "id", source = "user.id", defaultValue = "1L")
	UserPo boToPo(User user, UserBo userBo);
}
