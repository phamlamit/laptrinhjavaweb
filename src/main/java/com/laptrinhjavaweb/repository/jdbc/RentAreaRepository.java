package com.laptrinhjavaweb.repository.jdbc;

import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.entity.RentAreaEntity;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity>{
	Long save(RentAreaDTO rentAreaDTO);
}
