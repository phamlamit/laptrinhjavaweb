package com.laptrinhjavaweb.repository.jdbc;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;

public interface BuildingRepository {
	List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder);
	Boolean createBuilding(BuildingDTO buildingDTO);
	Boolean createRentArea(BuildingDTO buildingDTO);

}
