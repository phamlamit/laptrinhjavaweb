package com.laptrinhjavaweb.enums;

public enum TransactionTypeEnum {
    CSKH("Quá Trình Chăm Sóc Khác Hàng"), Xem_nha("Dẫn đi xem nhà");

    private final String typeTransaction;

    TransactionTypeEnum(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }
}
