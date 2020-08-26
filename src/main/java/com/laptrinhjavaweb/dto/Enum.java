package com.laptrinhjavaweb.dto;

public class Enum {
	enum District {
		Quan_1("Quận 1"), Quan_2("Quận 2"), Quan_3("Quận 3"), Quan_4("Quận 4"), Quan_5("Quận 6"), Quan_6("Quận 6"),
		Quan_7("Quận 8"), Quan_8("Quận 8"), Quan_9("Quận 9"), Quan_10("Quận 10"), Quan_11("Quận 11"),
		Quan_12("Quận 12"), Quan_Binh_Tan("Quận Bình Tân"), Quan_Binh_Thach("Quận Bình Thạnh"),
		Quan_Go_Vap("Quận Gò Vấp"), Quan_Phu_Nhuan("Quận Phú Nhuận"), Quan_Tan_Binh("Quận Tân Bình"),
		Quan_Tan_Phu("Quận Tân Phú"), Quan_Thu_Duc("Quận Thủ Đức"), Huyen_Binh_Chanh("Huyện Bình Chánh"),
		Huyen_Can_Gio("Huyện Cần Giờ"), Huyen_Cu_Chi("Huyện Củ Chi"), Huyen_Hoc_Mon("Huyện Hóc Môn"),
		Huyen_Nha_Be("Huyện Nhà Bè");

		private final String district;

		District(String district) {
			this.district = district;
		}

		public String getDistrict() {
			return this.district;
		}

	}

	enum TypeBuilding {
		Tang_tret("Tầng trệt"), Nguyen_can("Nguyên căn"), Noi_that("Nội thất");

		private final String typeBuilding;

		TypeBuilding(String typeBuilding) {
			this.typeBuilding = typeBuilding;
		}

		public String getTypeBuilding() {
			return typeBuilding;
		}

	}

	enum TypeTransaction {
		CSKH("Quá Trình Chăm Sóc Khác Hàng"), Xem_nha("Dẫn đi xem nhà");

		private final String typeTransaction;

		TypeTransaction(String typeTransaction) {
			this.typeTransaction = typeTransaction;
		}

		public String getTypeTransaction() {
			return typeTransaction;
		}

	}

}
