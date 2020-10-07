package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import org.springframework.stereotype.Component;

@Component
public class AssignmentBuildingConverter {
	ModelMapper modelMapper = new ModelMapper();

	public AssignmentBuildingDTO convertToDTO(AssignmentBuildingEntity assignmentBuildingEntity) {
		AssignmentBuildingDTO assignmentBuildingDTO = modelMapper.map(assignmentBuildingEntity,
				AssignmentBuildingDTO.class);
		return assignmentBuildingDTO;
	}

	public AssignmentBuildingEntity convertToEntity(AssignmentBuildingDTO assignmentBuildingDTO) {
		AssignmentBuildingEntity assignmentBuildingEntity = modelMapper.map(assignmentBuildingDTO,
				AssignmentBuildingEntity.class);
		return assignmentBuildingEntity;
	}
}
