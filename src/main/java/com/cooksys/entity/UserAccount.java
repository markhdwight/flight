package com.cooksys.entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.cooksys.pojo.Flight;

//@Entity
public class UserAccount {

	@Id
	@GeneratedValue
	private long userId;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	private ArrayList<Flight> flightHistory;
	
	public UserAccount()
	{
		
	}
	
}
