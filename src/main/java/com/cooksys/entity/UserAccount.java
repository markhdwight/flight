package com.cooksys.entity;

import java.util.ArrayList;

import javax.persistence.Entity;

import com.cooksys.pojo.Flight;

@Entity
public class UserAccount {

	private String username;
	
	private String password;
	
	private ArrayList<Flight> flightHistory;
	
}
