package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.AssignmentBuildingConverter;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.jdbc.AssignmentBuildingRepository;
import com.laptrinhjavaweb.repository.jdbc.BuildingRepository;
import com.laptrinhjavaweb.repository.jdbc.RentAreaRepository;
import com.laptrinhjavaweb.repository.jdbc.UserRepository;
import com.laptrinhjavaweb.repository.jdbc.impl.AssignmentBuildingRepositoryImpl;
import com.laptrinhjavaweb.repository.jdbc.impl.BuildingRepositoryImpl;
import com.laptrinhjavaweb.repository.jdbc.impl.RentAreaRepositoryImpl;
import com.laptrinhjavaweb.repository.jdbc.impl.UserRepositoryImpl;
import com.laptrinhjavaweb.service.BuildingService;

public class BuildingServiceImpl implements BuildingService {
	private BuildingRepository buildingRepository = new BuildingRepositoryImpl();
	private RentAreaRepository rentAreaRepository = new RentAreaRepositoryImpl();
	private BuildingConverter buildingConverter = new BuildingConverter();
	private UserRepository userRepository = new UserRepositoryImpl();
	private AssignmentBuildingRepository assignmentBuildingRepository = new AssignmentBuildingRepositoryImpl();
	private UserConverter userConverter = new UserConverter();
	private AssignmentBuildingConverter assignmentBuildingConverter = new AssignmentBuildingConverter();

	@Override
	public List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder) {
		// TODO Auto-generated method stub
		return buildingRepository.getBuildings(buildingSearchBuilder);
	}

	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		// open transasction

//		String rentArea = buildingDTO.getRentArea();
//		String[] valueRentArea = rentArea.split(",");
//
//		buildingDTO.setRentAreas(valueRentArea);
//		Long buildingId = buildingRepository.saveWithTransaction(buildingDTO);
		// chuyen tu buildingDTO -> buildingEntity

		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		Long buildingId = buildingRepository.save(buildingEntity);
		// RentAreaEntity rentAreaEntity = new RentAreaEntity();
		// rentAreaRepository.save(rentAreaEntity);

		return buildingConverter.convertToDto(buildingRepository.findById(buildingId));
	}

	@Override
	public List<BuildingDTO> fillAll() {
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		List<BuildingEntity> buildingEntity = buildingRepository.fillAll();
		for (BuildingEntity building : buildingEntity) {
			BuildingDTO buildingDTO = buildingConverter.convertToDto(building);
			result.add(buildingDTO);

		}
		return result;
	}

	@Override
	public List<BuildingDTO> delete(Long id) {
		buildingRepository.delete(id);
		List<BuildingDTO> result = fillAll();
		return result;
	}

	@Override
	public BuildingDTO update(BuildingDTO buildingDTO) {
		Long id = buildingDTO.getId();
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		id = buildingRepository.update(id, buildingEntity);
		buildingEntity = buildingRepository.findById(id);
		BuildingDTO result = buildingConverter.convertToDto(buildingEntity);
		return result;
	}

	public List<UserDTO> fillAll(Long buildingId) {
		List<UserDTO> result = new ArrayList<>();
		List<UserEntity> listUserEntity = userRepository.fillAll();
		for (UserEntity userEntity : listUserEntity) {
			UserDTO userDto = userConverter.convertToDto(userEntity);
			result.add(userDto);
		}
		List<AssignmentBuildingDTO> listAssignmentBuildingDTO = new ArrayList<>();
		List<AssignmentBuildingEntity> listAssignmentBuildingEntity = assignmentBuildingRepository.fillAll(buildingId);
		for (AssignmentBuildingEntity assignmentBuildingEntity : listAssignmentBuildingEntity) {
			AssignmentBuildingDTO assignmentBuildingDTO = assignmentBuildingConverter
					.convertToDTO(assignmentBuildingEntity);
			listAssignmentBuildingDTO.add(assignmentBuildingDTO);
		}
		for (AssignmentBuildingDTO assignmentBuildingDTO : listAssignmentBuildingDTO) {
			for (UserDTO userDto : result) {
				if (assignmentBuildingDTO.getStaffid() == userDto.getId()) {
					userDto.setChecked(true);
				}
			}
		}
		return result;
	}

	@Override
	public List<AssignmentBuildingDTO> updateUserAssignmentBuilding(Long buildingId, List<String> listId) {
		assignmentBuildingRepository.deleteByBuildingId(buildingId);
		List<AssignmentBuildingDTO> result = new ArrayList<AssignmentBuildingDTO>();
		for (String value : listId) {
			
			AssignmentBuildingEntity assignmentBuildingEntity= new AssignmentBuildingEntity();
			assignmentBuildingEntity.setBuildingid(buildingId);
			assignmentBuildingEntity.setStaffid(Long.parseLong(value));
			Long id = assignmentBuildingRepository.save(assignmentBuildingEntity);					
		}
		List<AssignmentBuildingEntity> list = assignmentBuildingRepository.fillAll();
		for (AssignmentBuildingEntity assignmentBuildingEntity : list) {
			AssignmentBuildingDTO assignmentBuildingDTO = assignmentBuildingConverter.convertToDTO(assignmentBuildingEntity);
			result.add(assignmentBuildingDTO);
		}
		return result;
	}

	

}
