package com.laptrinhjavaweb.repository.jdbc;

import java.util.List;

import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.StaffEntity;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity>{
	void delete(Long buildingId, Long staffId);
	boolean checked(Long buildingId, long staffId);
	List<Long> getStaff(Long buildingId);
}
