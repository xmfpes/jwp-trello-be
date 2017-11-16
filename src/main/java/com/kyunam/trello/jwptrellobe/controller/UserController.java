package com.kyunam.trello.jwptrellobe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kyunam.trello.jwptrellobe.domain.User;
import com.kyunam.trello.jwptrellobe.domain.UserDto;
import com.kyunam.trello.jwptrellobe.domain.UserRepository;

@Controller
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserRepository userRepository;
	@GetMapping("/signUp")
	public String signUpForm() {
		return "signUp";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto userDto){
		ResponseEntity<?> entity = null;
		String userId = userDto.getUserId();
		String password = userDto.getPassword();
		try {
			User user = userRepository.findByUserId(userId);
			if(user == null) {
				entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}else {
				entity = user.matchPassword(password);
			}
		}catch(Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@PostMapping("/api/users")
	public ResponseEntity<User> create(@RequestBody UserDto newUser) {
		ResponseEntity<User> entity = null;
		try {
			User user = new User(newUser);
			userRepository.save(user);
			entity = new ResponseEntity<User>(user, HttpStatus.CREATED);
		} catch(Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
