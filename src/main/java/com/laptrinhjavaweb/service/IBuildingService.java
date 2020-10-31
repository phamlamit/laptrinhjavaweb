package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    Map<String,String> getBuildingTypes();
    List<BuildingDTO> getBuildings(BuildingDTO buildingDTO);
    List<BuildingDTO> getBuildings();
}
