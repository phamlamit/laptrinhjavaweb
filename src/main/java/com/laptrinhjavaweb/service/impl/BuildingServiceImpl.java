package com.laptrinhjavaweb.service.impl;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.repository.jdbc.BuildingRepository;
import com.laptrinhjavaweb.repository.jdbc.RentAreaRepository;
import com.laptrinhjavaweb.repository.jdbc.impl.BuildingRepositoryImpl;
import com.laptrinhjavaweb.repository.jdbc.impl.RentAreaRepositoryImpl;
import com.laptrinhjavaweb.service.BuildingService;

public class BuildingServiceImpl implements BuildingService {
	private BuildingRepository buildingRepository = new BuildingRepositoryImpl();
	private RentAreaRepository rentAreaRepository = new RentAreaRepositoryImpl();

	@Override
	public List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder) {
		// TODO Auto-generated method stub
		return buildingRepository.getBuildings(buildingSearchBuilder);
	}

	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		Long buildingId = buildingRepository.save(buildingDTO);
		String rentArea = buildingDTO.getRentArea();
		String[] valueRentArea = rentArea.split(",");

		for (String value : valueRentArea) {
			RentAreaDTO rentAreaDTO = new RentAreaDTO();
			rentAreaDTO.setBuildingId(buildingId);
			rentAreaDTO.setValue(Integer.parseInt(value));
			rentAreaRepository.save(rentAreaDTO);
		}
		return buildingRepository.findById(buildingId);
	}

}
