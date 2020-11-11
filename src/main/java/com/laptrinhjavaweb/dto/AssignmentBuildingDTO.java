package com.laptrinhjavaweb.dto;

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


}
