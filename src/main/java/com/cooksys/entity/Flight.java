package com.cooksys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Flight implements Comparable<Flight> {
	
	@Id
	@GeneratedValue
	private long flightId;
	
	//Name of city where flight originates
	@Column
	private String origin;
	
	//Name of city where flight lands
	@Column
	private String destination;
	
	//How many hours flight is in the air
	@Column
	private long flightTime;
	
	//How many hours after the start of the day until the flight takes off
	@Column
	private long offset;
	
	
	public long getFlightId() {
		return flightId;
	}
	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public long getFlightTime() {
		return flightTime;
	}
	public void setFlightTime(long flightTime) {
		this.flightTime = flightTime;
	}
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	public Flight(String origin, String destination, long flightTime, long offset) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.flightTime = flightTime;
		this.offset = offset;
	}
	@Override	//Orders flights such that the earliest flight comes first
	public int compareTo(Flight o) {
		
		if(this.offset < o.getOffset())
			return -1;
		else if(this.offset == o.getOffset())
			return 0;
		else return 1;
	}
	
	

}
