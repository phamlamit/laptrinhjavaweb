package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.StaffDTO;
import com.laptrinhjavaweb.entity.StaffEntity;

public class UserConverter {
	ModelMapper modelMapper = new ModelMapper();

	public StaffDTO convertToDto(StaffEntity staffEntity) {
		StaffDTO staffDTO = modelMapper.map(staffEntity, StaffDTO.class);
		return staffDTO;
	}

	public StaffEntity convertToEntity(StaffDTO staffDTO) {
		StaffEntity staffEntity = modelMapper.map(staffDTO, StaffEntity.class);
		return staffEntity;
	}
	
}
