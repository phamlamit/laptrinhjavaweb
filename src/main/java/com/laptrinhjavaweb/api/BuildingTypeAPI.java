package com.laptrinhjavaweb.api;

import com.laptrinhjavaweb.dto.output.BuildingTypeOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuildingTypeAPI {
    @GetMapping("/building-types")
    public List<BuildingTypeOutput> getBuildingType() {
        BuildingTypeOutput buildingTypeOutput = new BuildingTypeOutput();
        return buildingTypeOutput.listBuildingTypeOutput();
    }
}
