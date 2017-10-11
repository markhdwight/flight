package com.cooksys.service;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.cooksys.dto.UserAccountDto;
import com.cooksys.entity.Trip;
import com.cooksys.entity.UserAccount;
import com.cooksys.mapper.UserAccountMapper;
import com.cooksys.pojo.Credentials;
import com.cooksys.repository.UserAccountJPARepository;
import com.cooksys.repository.UserAccountRepository;

@Service
public class UserAccountService 
{
	private UserAccountRepository userRepo;
	private UserAccountJPARepository userJPARepo;
	private UserAccountMapper userMapper;
	
	private EntityManager mgmt;

	public UserAccountService(EntityManager mgmt, UserAccountRepository userRepo, UserAccountJPARepository userJPARepo)
	{
		this.mgmt = mgmt;
		this.userRepo = userRepo;
		this.userJPARepo = userJPARepo;
	}
	
	public UserAccountDto getAccountById(long id) {
		
		return userMapper.toDto(userJPARepo.findByUserId(id));
	}
	
	public UserAccountDto getAccountByUsername(String username) {

		return userMapper.toDto(userJPARepo.findByUsername(username));
	}


	public UserAccountDto validateUser(Credentials credentials) {
		
		UserAccount user = userJPARepo.findByUsername(credentials.username);
		
		if(user.getPassword().equals(credentials.password))
		{
			return userMapper.toDto(user);
		}
		
		return null;
	}

	public UserAccountDto createUser(Credentials credentials) {
		
		return userMapper.toDto(userRepo.create(new UserAccount(credentials)));
	}
	
	public UserAccountDto addUserTrip(Credentials credentials,Trip trip)
	{
		UserAccount user = userJPARepo.findByUsername(credentials.username);
		
		user.addTrip(trip);
		
		return userMapper.toDto(userRepo.update(user));
	}
	
	public boolean userExists(String username)
	{
		//return userJPARepo.findByUsername(username) == null;
		
		for(UserAccount user : userRepo.getAllUsers())
		{
			if(user.getUsername().equals(username))
				return true;
		}
		return false;
	}

}
