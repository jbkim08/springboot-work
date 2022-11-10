package com.sec.busanit.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVO {

	private String username;
	private String password;
	private String nickname;
	private String role;
	private Date joindate;
	
	public UserVO(String username, String password, String nickname, String role) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.role = role;
	}
	
}
