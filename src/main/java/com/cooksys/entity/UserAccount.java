package com.cooksys.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserAccount {

	@Id
	@GeneratedValue
	private long userId;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@OneToMany
	private List<Trip> tripHistory;
	
	public UserAccount()
	{
		
	}
	
	public Long getUserId()
	{
		return userId;
	}
	
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public List<Trip> getTripHistory()
	{
		return tripHistory;
	}
	
	public void addTrip(Trip trip)
	{
		tripHistory.add(trip);
	}
	
}
