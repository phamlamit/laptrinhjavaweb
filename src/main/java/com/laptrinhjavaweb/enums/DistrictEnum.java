package com.laptrinhjavaweb.enums;

public enum DistrictEnum {
    Quan_1("Quận 1"),
    Quan_2("Quận 2"),
    Quan_3("Quận 3"),
    Quan_4("Quận 4"),
    Quan_5("Quận 6"),
    Quan_6("Quận 6"),
    Quan_7("Quận 8"),
    Quan_8("Quận 8"),
    Quan_9("Quận 9"),
    Quan_10("Quận 10"),
    Quan_11("Quận 11"),
    Quan_12("Quận 12"),
    Quan_Binh_Tan("Quận Bình Tân"),
    Quan_Binh_Thach("Quận Bình Thạnh"),
    Quan_Go_Vap("Quận Gò Vấp"),
    Quan_Phu_Nhuan("Quận Phú Nhuận"),
    Quan_Tan_Binh("Quận Tân Bình"),
    Quan_Tan_Phu("Quận Tân Phú"),
    Quan_Thu_Duc("Quận Thủ Đức"),
    Huyen_Binh_Chanh("Huyện Bình Chánh"),
    Huyen_Can_Gio("Huyện Cần Giờ"),
    Huyen_Cu_Chi("Huyện Củ Chi"),
    Huyen_Hoc_Mon("Huyện Hóc Môn"),
    Huyen_Nha_Be("Huyện Nhà Bè");

    private final String typeDistrict;

    DistrictEnum(String typeDistrict) {
        this.typeDistrict = typeDistrict;
    }

    public String getValue() {
        return this.typeDistrict;
    }
}
