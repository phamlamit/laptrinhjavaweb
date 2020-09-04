package com.laptrinhjavaweb.repository.jdbc;

import com.laptrinhjavaweb.entity.StaffEntity;

import java.util.List;

public interface StaffRepository extends JpaRepository<StaffEntity> {
    List<StaffEntity> fillAllStaff();
}
