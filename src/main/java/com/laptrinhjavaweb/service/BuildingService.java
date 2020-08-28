package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;

public interface BuildingService {
	List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder);

	BuildingDTO save(BuildingDTO buildingDTO);

	List<BuildingDTO> fillAll();

	List<BuildingDTO> delete(Long id);

	BuildingDTO update(BuildingDTO buildingDTO);

	List<UserDTO> fillAll(Long buildingId);
}
