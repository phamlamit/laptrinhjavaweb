package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    Map<String,String> getBuildingTypes();
    List<BuildingDTO> getBuildings(BuildingDTO buildingDTO);
    BuildingDTO save(BuildingDTO buildingDTO);
    BuildingDTO findById(Long id);
    List<UserDTO> updateUserAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);

    void delete(long buildingId);
}
