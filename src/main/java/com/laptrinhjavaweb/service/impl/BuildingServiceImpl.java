package com.laptrinhjavaweb.service.impl;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.repository.jdbc.BuildingRepository;
import com.laptrinhjavaweb.repository.jdbc.impl.BuildingRepositoryImpl;
import com.laptrinhjavaweb.service.BuildingService;

public class BuildingServiceImpl implements BuildingService{
	private BuildingRepository buildingRepository = new BuildingRepositoryImpl();

	@Override
	public List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder) {
		// TODO Auto-generated method stub
		return buildingRepository.getBuildings(buildingSearchBuilder);
	}

	@Override
	public Boolean createBuilding(BuildingDTO buildingDTO) {
		buildingRepository.createBuilding(buildingDTO);
		
		return true;
	}

	@Override
	public Boolean createRentArea(BuildingDTO buildingDTO) {
		buildingRepository.createRentArea(buildingDTO);
		return true;
	}

}
