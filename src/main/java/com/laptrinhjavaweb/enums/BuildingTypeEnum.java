package com.laptrinhjavaweb.enums;

public enum BuildingTypeEnum {
    Tang_tret("Tầng trệt"),
    Nguyen_can("Nguyên căn"),
    Noi_that("Nội thất");

    private final String typeBuilding;

    BuildingTypeEnum(String typeBuilding) {
        this.typeBuilding = typeBuilding;
    }

    public String getTypeBuilding() {
        return typeBuilding;
    }
}
