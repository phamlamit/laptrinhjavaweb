package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.jdbc.BuildingRepository;
import com.laptrinhjavaweb.repository.jdbc.RentAreaRepository;
import com.laptrinhjavaweb.repository.jdbc.impl.BuildingRepositoryImpl;
import com.laptrinhjavaweb.repository.jdbc.impl.RentAreaRepositoryImpl;
import com.laptrinhjavaweb.service.BuildingService;

public class BuildingServiceImpl implements BuildingService {
	private BuildingRepository buildingRepository = new BuildingRepositoryImpl();
	private RentAreaRepository rentAreaRepository = new RentAreaRepositoryImpl();
	private BuildingConverter buildingConverter = new BuildingConverter();

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

}
