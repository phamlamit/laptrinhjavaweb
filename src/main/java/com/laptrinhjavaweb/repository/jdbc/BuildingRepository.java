package com.laptrinhjavaweb.repository.jdbc;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;

public interface BuildingRepository extends JpaRepository<BuildingEntity>, BuildingRepositoryCustom {
	List<BuildingEntity> getBuildings(BuildingSearchBuilder buildingSearchBuilder);
	//Long save(BuildingDTO buildingDTO);
	//BuildingDTO findById(Long buildingId);
	Long saveWithTransaction(BuildingDTO buildingDTO);
}
