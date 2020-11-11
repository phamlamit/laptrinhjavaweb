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
        buildingEntity.setType(String.join(",", buildingDTO.getTypes()));
        if (buildingDTO.getId() != null) {
            List<RentAreaEntity> rentAreaEntityList = new ArrayList<>();
            if (buildingRepository.findOne(buildingDTO.getId()) != null) {
                BuildingEntity oldBuildingEntity = buildingRepository.findOne(buildingDTO.getId());
                buildingEntity.setCreatedBy(oldBuildingEntity.getCreatedBy());
                buildingEntity.setCreatedDate(oldBuildingEntity.getCreatedDate());
            }
            rentAreaEntityList = rentAreaRepository.findByBuilding_Id(buildingDTO.getId());
            rentAreaRepository.delete(rentAreaEntityList);
        }
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
        String[] Types = result.getType().split(",");
        result.setTypes(Types);
        // xu ly dien tich thue
        List<RentAreaEntity> rentAreaEntityList = rentAreaRepository.findByBuilding_Id(id);
        StringBuilder rentAreas = new StringBuilder("");
        for (RentAreaEntity rentAreaEntity : rentAreaEntityList) {
            rentAreas.append(rentAreaEntity.getValue() + "");
            if (rentAreaEntity != rentAreaEntityList.get(rentAreaEntityList.size() - 1)) {
                rentAreas.append(",");
            }
        }
        result.setRentArea(rentAreas.toString());
        return result;
    }

    @Override
    @Transactional
    public void updateUserAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        //java 7
        BuildingEntity buildingEntity = buildingRepository.findOne(assignmentBuildingDTO.getBuildingId());
        List<UserEntity> newUserEntityList = new ArrayList<>();
        for (Long userid : assignmentBuildingDTO.getStaffId()) {
            UserEntity userEntity = userRepository.findOne(userid);
            newUserEntityList.add(userEntity);
        }
        buildingEntity.setStaffs(newUserEntityList);
        buildingRepository.save(buildingEntity);
        //java 8
    }

    @Override
    @Transactional
    public void delete(long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findOne(buildingId);
        List<RentAreaEntity> rentAreaEntityList = rentAreaRepository.findByBuilding_Id(buildingId);
        rentAreaRepository.delete(rentAreaEntityList);
        //buildingEntity.setStaffs(userEntityList);
        //buildingEntity.getStaffs().remove(buildingEntity);
        /*for (UserEntity item: userEntityList) {
            item.getBuildings().remove(buildingEntity);
        }*/
        buildingRepository.delete(buildingEntity);
    }

}
