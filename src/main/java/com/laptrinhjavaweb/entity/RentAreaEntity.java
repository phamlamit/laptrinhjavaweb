package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity extends BaseEntity {
    @Column(name = "value")
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;

    public Integer getValue() {
        return value;
    }

    public RentAreaEntity setValue(Integer value) {
        this.value = value;
        return this;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public RentAreaEntity setBuilding(BuildingEntity building) {
        this.building = building;
        return this;
    }
}
