package com.laptrinhjavaweb.repository.jdbc;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;

public interface BuildingRepository {
	List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder);
	Long save(BuildingDTO buildingDTO);
	BuildingDTO findById(Long buildingId);

}
