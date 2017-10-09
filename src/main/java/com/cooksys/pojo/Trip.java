package com.cooksys.pojo;

import java.util.ArrayList;

public class Trip {
	
	public ArrayList<Flight> flights;
	
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

}
