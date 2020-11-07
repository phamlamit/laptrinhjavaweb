package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.BuildingTypeEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private RentAreaRepository rentAreaRepository;

    public List<UserDTO> getAssignmentBuilding(Long id) {
        List<UserDTO> result = new ArrayList<>();
        List<UserEntity> userEntityList = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        for (UserEntity userEntity : userEntityList) {
            UserDTO userDTO = userConverter.convertToDto(userEntity);
            userDTO.setChecked(IsAssigmentBuilding(id, userEntity));
            result.add(userDTO);
        }
        return result;
    }

    boolean IsAssigmentBuilding(Long buildingId, UserEntity userEntity) {
        UserEntity userEntityChecked = userRepository.findByIdIsAndBuildings_IdIs(userEntity.getId(), buildingId);
        if (userEntityChecked != null && userEntity.getId() == userEntityChecked.getId()) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String, String> getBuildingTypes() {
        Map<String, String> results = new LinkedHashMap<>();
        for (BuildingTypeEnum buildingTypeEnum : BuildingTypeEnum.values()) {
            results.put(buildingTypeEnum.name(), buildingTypeEnum.getValue());
        }
        return results;

    }

    @Override
    public List<BuildingDTO> getBuildings(BuildingDTO buildingDTO) {
        List<BuildingEntity> listBuildingEntity = buildingRepository.getBuildings(buildingDTO);
        List<BuildingDTO> result = listBuildingEntity.stream()
                .map(item -> buildingConverter.convertToDto(item))
                .collect(Collectors.toList());
        return result;

    }

    @Override
    @Transactional
    public BuildingDTO save(BuildingDTO buildingDTO) {
        String rentArea = buildingDTO.getRentArea();
        String[] valueRentArea = rentArea.split(",");
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        buildingEntity.setTypes(String.join(",", buildingDTO.getTypes()));
        List<RentAreaEntity> rentAreaEntityList = new ArrayList<>();
        for (String item : valueRentArea) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaEntity.setValue(Integer.parseInt(item));
            rentAreaEntityList.add(rentAreaEntity);
        }
        buildingEntity.setRentAreas(rentAreaEntityList);
        buildingEntity = buildingRepository.save(buildingEntity);
        BuildingDTO buildingDTOResult = buildingConverter.convertToDto(buildingEntity);
        return buildingDTOResult;
    }

    @Override
    public BuildingDTO findById(Long id) {
        BuildingDTO result = buildingConverter.convertToDto(buildingRepository.findOne(id));
        //xu ly loai toa nha
        // xu ly dien tich thue
        return result;
    }

    @Override
    public List<UserDTO> updateUserAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        List<UserDTO> result = new ArrayList<>();
        BuildingEntity buildingEntity = buildingRepository.findOne(assignmentBuildingDTO.getBuildingId());
        Long[] newUsers = assignmentBuildingDTO.getStaffId();
        List<UserEntity> newUserEntityList = new ArrayList<>();
        for (Long userid : newUsers) {
            UserEntity userEntity = userRepository.findOne(userid);
            newUserEntityList.add(userEntity);
        }
        buildingEntity.setStaffs(newUserEntityList);
        buildingEntity = buildingRepository.save(buildingEntity);
        return null;
    }

    @Override
    public void delete(long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findOne(buildingId);
        List<RentAreaEntity> rentAreaEntityList = rentAreaRepository.findByBuilding_Id(buildingId);
        List<UserEntity> userEntityList = userRepository.findByBuildings_Id(buildingId);
        rentAreaRepository.delete(rentAreaEntityList);
        buildingEntity.setStaffs(userEntityList);
        buildingRepository.delete(buildingEntity);
    }

    private List<Long> getStaff(List<UserEntity> userEntityList) {
        List<Long> result = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            result.add(userEntity.getId());
        }
        return result;
    }

}
