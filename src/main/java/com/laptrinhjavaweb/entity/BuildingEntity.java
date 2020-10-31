package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "ward")
    private String ward;
    @Column(name = "street")
    private String street;
    @Column(name = "district")
    private String district;
    @Column(name = "structure")
    private String structure;
    @Column(name = "numberofbasement")
    private Integer numberOfBasement;
    @Column(name = "floorarea")
    private Integer floorArea;
    @Column(name = "direction")
    private String direction;
    @Column(name = "level")
    private String level;
    @Column(name = "rentprice")
    private Integer rentPrice;
    @Column(name = "rentpricedescription")
    private String rentPriceDescription;
    @Column(name = "servicefee")
    private String serviceFee;
    @Column(name = "carfee")
    private String carFee;
    @Column(name = "motofee")
    private String motoFee;
    @Column(name = "overtimefee")
    private String overtimeFee;
    @Column(name = "waterfee")
    private String waterFee;
    @Column(name = "electricityfee")
    private String electricityFee;
    @Column(name = "deposit")
    private String deposit;
    @Column(name = "payment")
    private String payment;
    @Column(name = "renttime")
    private String rentTime;
    @Column(name = "decorationtime")
    private Double decorationTime;
    @Column(name = "brokeragefee")
    private Double brokerageFee;
    @Column(name = "types")
    private String types;
    @OneToMany(mappedBy = "building")
    private List<RentAreaEntity> rentAreas = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid"),
            inverseJoinColumns = @JoinColumn(name = "staffid"))
    private List<UserEntity> staffs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public BuildingEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getWard() {
        return ward;
    }

    public BuildingEntity setWard(String ward) {
        this.ward = ward;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public BuildingEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public BuildingEntity setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getStructure() {
        return structure;
    }

    public BuildingEntity setStructure(String structure) {
        this.structure = structure;
        return this;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public BuildingEntity setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
        return this;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public BuildingEntity setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
        return this;
    }

    public String getDirection() {
        return direction;
    }

    public BuildingEntity setDirection(String direction) {
        this.direction = direction;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public BuildingEntity setLevel(String level) {
        this.level = level;
        return this;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public BuildingEntity setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
        return this;
    }

    public String getRentPriceDescription() {
        return rentPriceDescription;
    }

    public BuildingEntity setRentPriceDescription(String rentPriceDescription) {
        this.rentPriceDescription = rentPriceDescription;
        return this;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public BuildingEntity setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
        return this;
    }

    public String getCarFee() {
        return carFee;
    }

    public BuildingEntity setCarFee(String carFee) {
        this.carFee = carFee;
        return this;
    }

    public String getMotoFee() {
        return motoFee;
    }

    public BuildingEntity setMotoFee(String motoFee) {
        this.motoFee = motoFee;
        return this;
    }

    public String getOvertimeFee() {
        return overtimeFee;
    }

    public BuildingEntity setOvertimeFee(String overtimeFee) {
        this.overtimeFee = overtimeFee;
        return this;
    }

    public String getWaterFee() {
        return waterFee;
    }

    public BuildingEntity setWaterFee(String waterFee) {
        this.waterFee = waterFee;
        return this;
    }

    public String getElectricityFee() {
        return electricityFee;
    }

    public BuildingEntity setElectricityFee(String electricityFee) {
        this.electricityFee = electricityFee;
        return this;
    }

    public String getDeposit() {
        return deposit;
    }

    public BuildingEntity setDeposit(String deposit) {
        this.deposit = deposit;
        return this;
    }

    public String getPayment() {
        return payment;
    }

    public BuildingEntity setPayment(String payment) {
        this.payment = payment;
        return this;
    }

    public String getRentTime() {
        return rentTime;
    }

    public BuildingEntity setRentTime(String rentTime) {
        this.rentTime = rentTime;
        return this;
    }

    public Double getDecorationTime() {
        return decorationTime;
    }

    public BuildingEntity setDecorationTime(Double decorationTime) {
        this.decorationTime = decorationTime;
        return this;
    }

    public Double getBrokerageFee() {
        return brokerageFee;
    }

    public BuildingEntity setBrokerageFee(Double brokerageFee) {
        this.brokerageFee = brokerageFee;
        return this;
    }

    public List<UserEntity> getStaffs() {
        return staffs;
    }

    public BuildingEntity setStaffs(List<UserEntity> staffs) {
        this.staffs = staffs;
        return this;
    }
}
