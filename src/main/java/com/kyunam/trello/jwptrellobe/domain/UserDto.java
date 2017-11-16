package com.kyunam.trello.jwptrellobe.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userId;
	private String password;
	private String email;

	public UserDto() {}
	public UserDto(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
	public UserDto(String userId, String email, String password) {
		this.userId = userId;
		this.password = password;
		this.email = email;
	}
}
