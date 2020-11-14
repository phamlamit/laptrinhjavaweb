package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByUserNameAndStatus(String name, int status);
    List<UserEntity> findByStatusAndRoles_Code(Integer status, String Role_Code);
    UserEntity findByIdIsAndBuildings_IdIs(Long userId, Long buildingId);
    UserEntity findByIdIsAndCustomers_IdIs(Long userId, Long buildingId);
}
