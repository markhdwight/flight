package com.cooksys.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Trip {
	
	@Id
	@GeneratedValue
	private long tripId;
	
	@OneToMany
	private List<Flight> flights;
	
	public Trip()
	{
		flights = new ArrayList<Flight>();
	}
	
	public long getTripDuration()
	{
		long endTime = flights.get(flights.size()-1).getOffset() + flights.get(flights.size()-1).getFlightTime();
		long startTime = flights.get(0).getOffset();
		
		return endTime - startTime;
	}
	
	public long getLayoverTime()
	{
		long duration = getTripDuration();
		long airTime = 0;
		
		for(Flight f : flights)
		{
			airTime += f.getFlightTime();
		}
		
		return duration - airTime;
	}
	
	public boolean contains(String city)
	{
		for(Flight f : flights)
		{
			if(f.getOrigin().equals(city) || f.getDestination().equals(city))
				return true;
		}
		
		return false;
	}
	
	public void addStop(Flight flight)
	{
		flights.add(flight);
	}
	
	public int numStops()
	{
		return flights.size();
	}

	public Flight getLastStop() 
	{
		return flights.get(flights.size()-1);
	}

}
