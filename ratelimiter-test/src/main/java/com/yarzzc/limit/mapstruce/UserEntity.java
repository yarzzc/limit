package com.yarzzc.limit.mapstruce;

import lombok.Data;

import java.util.Date;

/**
 * entity
 *
 * @author Mr.Moonzzc
 * @date 2022-09-07 17:49
 **/
@Data
public class UserEntity {
	private Long id;
	private Date gmtCreate;
	private Date createTime;
	private Long buyerId;
	private Long age;
	private String userNick1;
	private String userVerified;
}
