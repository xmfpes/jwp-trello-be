package com.kyunam.trello.jwptrellobe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique = true, length=50)
	private String userId;
	@Column(nullable = false, length=50)
	private String password;
	@Column(nullable = true, length=100)
	private String email;
	
	public User(UserDto user) {
		this.userId = user.getUserId();
		this.password = user.getPassword();
		this.email = user.getEmail();
	}
	public User(String userId, String password, String email) {
		this.userId = userId;
		this.password = password;
		this.email = email;
	}
}
