package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.AssignmentBuildingConverter;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.StaffDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.StaffEntity;
import com.laptrinhjavaweb.repository.jdbc.AssignmentBuildingRepository;
import com.laptrinhjavaweb.repository.jdbc.BuildingRepository;
import com.laptrinhjavaweb.repository.jdbc.RentAreaRepository;
import com.laptrinhjavaweb.repository.jdbc.StaffRepository;
import com.laptrinhjavaweb.repository.jdbc.impl.AssignmentBuildingRepositoryImpl;
import com.laptrinhjavaweb.repository.jdbc.impl.BuildingRepositoryImpl;
import com.laptrinhjavaweb.repository.jdbc.impl.RentAreaRepositoryImpl;
import com.laptrinhjavaweb.repository.jdbc.impl.StaffRepositoryImpl;
import com.laptrinhjavaweb.service.BuildingService;

public class BuildingServiceImpl implements BuildingService {
	private BuildingRepository buildingRepository = new BuildingRepositoryImpl();
	private RentAreaRepository rentAreaRepository = new RentAreaRepositoryImpl();
	private BuildingConverter buildingConverter = new BuildingConverter();
	private StaffRepository staffRepository = new StaffRepositoryImpl();
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
		id = buildingRepository.update(buildingEntity);
		buildingEntity = buildingRepository.findById(id);
		BuildingDTO result = buildingConverter.convertToDto(buildingEntity);
		return result;
	}

	public List<StaffDTO> fillAll(Long buildingId) {
		List<StaffDTO> result = new ArrayList<>();
		List<StaffEntity> listStaffEntity = staffRepository.fillAllStaff();
		for (StaffEntity staffEntity : listStaffEntity) {
			StaffDTO staffDto = userConverter.convertToDto(staffEntity);
			staffDto.setChecked(assignmentBuildingRepository.checked(buildingId, staffDto.getId()));
			result.add(staffDto);
		}
		return result;
	}

	@Override
	public List<StaffDTO> updateUserAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
		List<StaffDTO> result = new ArrayList<>();
		List<Long> oldListStaff = assignmentBuildingRepository.getStaff(assignmentBuildingDTO.getBuildingId());
		List<Long> newListStaff = assignmentBuildingDTO.convertToList();
		List<Long> checkedList = new ArrayList<>();
		List<Long> unCheckedList = new ArrayList<>();
		for(Long newId : newListStaff){
			if(!oldListStaff.contains(newId)){
				unCheckedList.add(newId);
			}
		}
		for(Long oldId : oldListStaff){
			if(!newListStaff.contains(oldId)){
				checkedList.add(oldId);
			}
		}
		if(!checkedList.isEmpty()){
			for(Long id :  checkedList){
				assignmentBuildingRepository.delete(assignmentBuildingDTO.getBuildingId(),id);
			}
		}
		if(!unCheckedList.isEmpty()){
			for(Long id : unCheckedList){
				AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
				assignmentBuildingEntity.setBuildingid(assignmentBuildingDTO.getBuildingId());
				assignmentBuildingEntity.setStaffid(id);
				assignmentBuildingRepository.save(assignmentBuildingEntity);
			}
		}

		return fillAll(assignmentBuildingDTO.getBuildingId());
	}

	@Override
	public BuildingDTO getBuildings(Long id) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity = buildingRepository.findById(id);
		BuildingDTO result = buildingConverter.convertToDto(buildingEntity);
		return result;
	}


}
