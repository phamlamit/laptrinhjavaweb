package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.impl.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    @PostMapping("/api/building")
    public BuildingDTO createBuilding(@RequestBody BuildingDTO buildingDTO) {
        return buildingService.save(buildingDTO);
    }

    @GetMapping("/api/building/assingment-building")
    public List<UserDTO> getAssignmentBuilding(@RequestParam("id") String id) {
        List<UserDTO> result = buildingService.getAssignmentBuilding(Long.parseLong(id));
        return result;
    }

    @PostMapping("/api/building/assignemt-building/update")
    public void updateUserAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        buildingService.updateUserAssignmentBuilding(assignmentBuildingDTO);
    }

    @GetMapping("api/buildings/delete")
    public void deleteBuilding(@RequestParam(("id")) String id) {
        buildingService.delete(Long.parseLong(id));
    }
}
