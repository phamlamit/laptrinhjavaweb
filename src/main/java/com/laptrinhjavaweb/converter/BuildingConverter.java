package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.entity.BuildingEntity;
import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.BuildingDTO;
import org.springframework.stereotype.Component;

@Component
public class BuildingConverter {
	ModelMapper modelMapper = new ModelMapper();

	public BuildingDTO convertToDto(BuildingEntity buildingEntity) {
		BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
		return buildingDTO;
	}

	public BuildingEntity convertToEntity(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
		return buildingEntity;
	}

}
