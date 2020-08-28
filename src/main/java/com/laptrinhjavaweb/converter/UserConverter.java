package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;

public class UserConverter {
	ModelMapper modelMapper = new ModelMapper();

	public UserDTO convertToDto(UserEntity userEntity) {
		UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
		return userDTO;
	}

	public UserEntity convertToEntity(UserDTO userDTO) {
		UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
		return userEntity;
	}
	
}
