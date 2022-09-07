package com.yarzzc.limit.mapstruce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mr.Moonzzc
 * @date 2022-09-07 18:47
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Long id;
	private String userNick;
}
