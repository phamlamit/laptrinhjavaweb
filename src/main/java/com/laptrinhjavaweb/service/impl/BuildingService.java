package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enums.BuildingTypeEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<BuildingDTO> getBuildings() {
        List<BuildingEntity> buildingEntityList = buildingRepository.findAll();
        List<BuildingDTO> result = buildingEntityList.stream()
                .map(item -> buildingConverter.convertToDto(item))
                .collect(Collectors.toList());
        return result;
    }
}
