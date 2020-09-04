package com.laptrinhjavaweb.repository.jdbc.impl;

import com.laptrinhjavaweb.entity.StaffEntity;
import com.laptrinhjavaweb.repository.jdbc.StaffRepository;

import java.util.List;

public class StaffRepositoryImpl extends SimpleJpaRepositoryImpl<StaffEntity> implements StaffRepository {


    @Override
    public List<StaffEntity> fillAllStaff() {
        String sql = "SELECT * FROM user INNER JOIN user_role on user.id = user_role.userid WHERE user_role.roleid =2";
        return fillAll(sql);
    }
}
