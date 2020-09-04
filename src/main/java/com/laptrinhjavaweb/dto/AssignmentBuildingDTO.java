package com.laptrinhjavaweb.dto;

import java.util.ArrayList;
import java.util.List;

public class AssignmentBuildingDTO {
    private Long buildingId;
    private Long[] staffId;


    public Long[] getStaffId() {
        return staffId;
    }

    public void setStaffId(Long[] staffId) {
        this.staffId = staffId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public List<Long> convertToList (){
        List<Long> result = new ArrayList<>();
        for(Long id : staffId){
            result.add(id);
        }
        return result;
    }


}
