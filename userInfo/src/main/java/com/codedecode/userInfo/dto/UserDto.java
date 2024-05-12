package com.codedecode.userInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private int userId;
	private String userName;
	private String UserPassword;
	private String address;
	private String city;
}
