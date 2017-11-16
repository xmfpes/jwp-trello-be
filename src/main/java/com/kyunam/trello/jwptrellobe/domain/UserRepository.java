package com.kyunam.trello.jwptrellobe.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUserId(String userId);
}
