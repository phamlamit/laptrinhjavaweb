package com.laptrinhjavaweb.dto.output;

import com.laptrinhjavaweb.enums.BuildingTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class BuildingTypeOutput {
    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<BuildingTypeOutput> listBuildingTypeOutput() {
        List<BuildingTypeOutput> listBuildingTypeOutput = new ArrayList<BuildingTypeOutput>();
        for (BuildingTypeEnum buildingTypeEnum : BuildingTypeEnum.values()) {
            BuildingTypeOutput buildingTypeOutput = new BuildingTypeOutput();
            buildingTypeOutput.setCode(buildingTypeEnum.name());
            buildingTypeOutput.setValue(buildingTypeEnum.getTypeBuilding());
            listBuildingTypeOutput.add(buildingTypeOutput);
        }
        return listBuildingTypeOutput;
    }
}
