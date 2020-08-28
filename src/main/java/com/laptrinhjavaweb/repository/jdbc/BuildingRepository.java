package com.laptrinhjavaweb.repository.jdbc;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity>{
	List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder);
	//Long save(BuildingDTO buildingDTO);
	//BuildingDTO findById(Long buildingId);
	Long saveWithTransaction(BuildingDTO buildingDTO);

}
