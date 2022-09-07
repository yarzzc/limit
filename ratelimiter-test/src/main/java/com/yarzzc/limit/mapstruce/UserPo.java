package com.yarzzc.limit.mapstruce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * po
 *
 * @author Mr.Moonzzc
 * @date 2022-09-07 17:45
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPo {
	private Long id;
	private Date gmtCreate;
	private Date createTime;
	private Long buyerId;
	private Long age;
	private String userNick;
	private String userVerified;
}
