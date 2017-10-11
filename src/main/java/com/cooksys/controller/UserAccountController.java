package com.cooksys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.dto.UserAccountDto;
import com.cooksys.pojo.Credentials;
import com.cooksys.pojo.TripSubmission;
import com.cooksys.service.UserAccountService;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserAccountController {
	
	@Autowired
	UserAccountService userService;

	public UserAccountController(UserAccountService userService)
	{
		this.userService = userService;
	}

	@RequestMapping("{id}")
	public UserAccountDto getAccountById(@PathVariable long id)
	{
		return userService.getAccountById(id);
	}
	
	@RequestMapping("{username}")
	public UserAccountDto getAccountByUsername(@PathVariable String username)
	{
		return userService.getAccountByUsername(username);
	}
	
	@RequestMapping(value = "validate", method = RequestMethod.GET)
	public UserAccountDto validateUser(@RequestBody Credentials credentials)
	{
		return userService.validateUser(credentials);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public UserAccountDto createUser(@RequestBody Credentials credentials)
	{
		return userService.createUser(credentials);
	}
	
	@RequestMapping(method = RequestMethod.PATCH)
	public UserAccountDto addUserTrip(@RequestBody TripSubmission tripSub)
	{
		return userService.addUserTrip(tripSub.credentials, tripSub.trip);
	}
	
}
