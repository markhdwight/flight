package com.cooksys.service;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cooksys.component.FlightGenerator;
import com.cooksys.pojo.Flight;
import com.cooksys.pojo.Trip;

@Service
public class FlightService {

	@Autowired
	FlightGenerator generator;

	private ArrayList<Flight> flightList = new ArrayList<>();
	
	public ArrayList<Flight> getDailyFlightList()
	{
		return flightList;
	}
	
	//The fixedDelay parameter determines how often a new day is generated as expressed in milliseconds
	@Scheduled(fixedDelay=5000)
	private void refreshFlights()
	{
		flightList = generator.generateNewFlightList();
		Collections.sort(flightList);
	}
	
	public ArrayList<Trip> getValidTrips(String start,String end)
	{
		if(start.equals(end))
			return null;
		
		ArrayList<Trip> trips = new ArrayList<Trip>();
		
		for(Flight f : flightList)
		{
			if(f.getOrigin().equals(start))
			{
				if(f.getDestination().equals(start))	//avoid cycles
					continue;
				
				Trip trip = new Trip();
				trip.addStop(f);
				
				if(f.getDestination().equals(end))	//checks to see if the current trip is a direct flight to the desired destination
				{
					trips.add(trip);
				}
				else
				{
					trip = findConnectingFlights(f.getOffset()+f.getFlightTime()+1,f.getDestination(),end,trip);
					//trip.addAll(findConnectingFlights(f.getOffset()+f.getFlightTime()+1,f.getDestination(),end));
					
					if(trip.getLastStop().getDestination().equals(end))	//Double check to make sure the planned route would reach the proper destination
					{
						trips.add(trip);
					}					
				}
			}
		}
		
		return trips;
	}
	
	private Trip findConnectingFlights(long earliestTime, String start, String end, Trip trip)
	{
		Trip tripSegment = trip;
		
		for(Flight f : flightList)
		{
			if(f.getOrigin().equals(start) && f.getOffset() >= earliestTime && !trip.contains(f.getDestination()))
			{
				tripSegment.addStop(f);
				
				if(f.getDestination().equals(end))
				{
					return tripSegment;
				}
				else
				{
					return findConnectingFlights(f.getOffset()+f.getFlightTime()+1,f.getDestination(),end,tripSegment);
				}
			}
		}
		
		return tripSegment;
	}
	
}
