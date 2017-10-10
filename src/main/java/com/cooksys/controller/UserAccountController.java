package com.cooksys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.UserAccount;
import com.cooksys.service.UserAccountService;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserAccountController {
	
	@Autowired
	UserAccountService userService;

	@RequestMapping("{id}")
	public UserAccount getAccountById(@PathVariable long id)
	{
		return null;
	}
	
}
