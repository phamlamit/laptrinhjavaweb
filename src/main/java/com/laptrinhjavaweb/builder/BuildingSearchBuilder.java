package com.laptrinhjavaweb.builder;

public class BuildingSearchBuilder {
	// required parameters
	private String name;
	private String street;
	private String district;
	private Integer numberOfBasement;
	private Integer floorArea;
	private String direction;
	private String level;
	private Integer areaFrom;
	private Integer areaTo;
	private Double rentPriceFrom;
	private Double rentPriceTo;
	private String nameEmployeeInCharge;
	private String phone;
	private String ward;
	private String[] types = new String[] {};

	public String getStreet() {
		return street;
	}

	public String getDirection() {
		return direction;
	}

	public String getLevel() {
		return level;
	}

	public Integer getAreaFrom() {
		return areaFrom;
	}

	public Integer getAreaTo() {
		return areaTo;
	}

	public Double getRentPriceFrom() {
		return rentPriceFrom;
	}

	public Double getRentPriceTo() {
		return rentPriceTo;
	}

	public String getNameEmployeeInCharge() {
		return nameEmployeeInCharge;
	}

	public String getPhone() {
		return phone;
	}

	public String getName() {
		return name;
	}

	public String getWard() {
		return ward;
	}

	public String getDistrict() {
		return district;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	private BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.ward = builder.ward;
		this.district = builder.district;
		this.floorArea = builder.floorArea;
		this.numberOfBasement = builder.numberOfBasement;
		this.types = builder.types;
		this.areaFrom = builder.areaFrom;
		this.areaTo = builder.areaTo;
		this.direction = builder.direction;
		this.level = builder.level;
		this.phone = builder.phone;
		this.rentPriceFrom = builder.rentPriceFrom;
		this.rentPriceTo = builder.rentPriceTo;
		this.street = builder.street;
		this.nameEmployeeInCharge = builder.nameEmployeeInCharge;
	}

	public String[] getTypes() {
		return types;
	}

	// Builder Class
	public static class Builder {
		private String name;
		private String street;
		private String direction;
		private String level;
		private Integer areaFrom;
		private Integer areaTo;
		private Double rentPriceFrom;
		private Double rentPriceTo;
		private String nameEmployeeInCharge;
		private String phone;
		private String ward;
		private String district;
		private Integer numberOfBasement;
		private Integer floorArea;
		private String[] types = new String[] {};

		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}

		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}

		public Builder setLevel(String level) {
			this.level = level;
			return this;
		}

		public Builder setAreaFrom(Integer areaFrom) {
			this.areaFrom = areaFrom;
			return this;
		}

		public Builder setAreaTo(Integer areaTo) {
			this.areaTo = areaTo;
			return this;
		}

		public Builder setRentPriceFrom(Double rentPriceFrom) {
			this.rentPriceFrom = rentPriceFrom;
			return this;
		}

		public Builder setRentPriceTo(Double rentPriceTo) {
			this.rentPriceTo = rentPriceTo;
			return this;
		}

		public Builder setNameEmployeeInCharge(String nameEmployeeInCharge) {
			this.nameEmployeeInCharge = nameEmployeeInCharge;
			return this;
		}

		public Builder setPhone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}

		public Builder setDistrict(String district) {
			this.district = district;
			return this;
		}

		public Builder setNumberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}

		public Builder setFloorArea(Integer floorArea) {
			this.floorArea = floorArea;
			return this;
		}

		public Builder setTypes(String[] types) {
			this.types = types;
			return this;
		}
		

	}

}
