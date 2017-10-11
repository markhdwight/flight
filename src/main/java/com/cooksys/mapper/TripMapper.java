package com.cooksys.mapper;

import org.mapstruct.Mapper;

import com.cooksys.dto.TripDto;
import com.cooksys.entity.Trip;

@Mapper(componentModel = "spring")
public interface TripMapper {
	
	public Trip fromDto(TripDto dto);
	public TripDto toDto(Trip trip);

}
