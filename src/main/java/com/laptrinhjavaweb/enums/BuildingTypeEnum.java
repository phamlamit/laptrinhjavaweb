package com.laptrinhjavaweb.enums;

public enum BuildingTypeEnum {
    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất");

    private final String typeBuilding;

    BuildingTypeEnum(String typeBuilding) {
        this.typeBuilding = typeBuilding;
    }

    public String getValue() {
        return typeBuilding;
    }
}
