package com.cooksys.service;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cooksys.component.FlightGenerator;
import com.cooksys.pojo.Flight;

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
	
	public ArrayList<ArrayList<Flight>> getValidTrips(String start,String end)
	{
		if(start.equals(end))
			return null;
		
		ArrayList<ArrayList<Flight>> trips = new ArrayList<ArrayList<Flight>>();
		
		for(Flight f : flightList)
		{
			if(f.getOrigin().equals(start))
			{
				if(f.getDestination().equals(start))	//avoid cycles
					continue;
				
				ArrayList<Flight> trip = new ArrayList<Flight>();
				trip.add(f);
				
				if(f.getDestination().equals(end))
				{
					trips.add(trip);
				}
				else
				{
					trip.addAll(findConnectingFlights(f.getOffset()+f.getFlightTime()+1,f.getDestination(),end));
					
					if(trip.get(trip.size()-1).getDestination().equals(end))	//Double check to make sure the planned route would reach the proper destination
					{
						trips.add(trip);
					}					
				}
			}
		}
		
		return trips;
	}
	
	private ArrayList<Flight> findConnectingFlights(long earliestTime, String start, String end)
	{
		ArrayList<Flight> tripSegment = new ArrayList<Flight>();
		
		for(Flight f : flightList)
		{
			if(f.getOrigin().equals(start) && f.getOffset() >= earliestTime)
			{
				tripSegment.add(f);
				
				if(f.getDestination().equals(end))
				{
					return tripSegment;
				}
				else
				{
					tripSegment.addAll(findConnectingFlights(f.getOffset()+f.getFlightTime()+1,f.getDestination(),end));
					return tripSegment;
				}
			}
		}
		
		return tripSegment;
	}
	
}
