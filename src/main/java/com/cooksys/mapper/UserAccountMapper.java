package com.cooksys.mapper;

import org.mapstruct.Mapper;

import com.cooksys.dto.UserAccountDto;
import com.cooksys.entity.UserAccount;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {

	public UserAccount fromDto(UserAccountDto dto);
	public UserAccountDto toDto(UserAccount user);	

}
