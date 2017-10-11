package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.UserAccount;

public interface UserAccountJPARepository extends JpaRepository<UserAccount, Long> {
	
	UserAccount findByUserId(long userId);
	
	UserAccount findByUsername(String username);

}
