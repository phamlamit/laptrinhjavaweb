package com.laptrinhjavaweb.dto.input;

public class TransactionInput {
    private Long customerId;
    private String code;
    private String node;

    public Long getCustomerId() {
        return customerId;
    }

    public TransactionInput setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getCode() {
        return code;
    }

    public TransactionInput setCode(String code) {
        this.code = code;
        return this;
    }

    public String getNode() {
        return node;
    }

    public TransactionInput setNode(String node) {
        this.node = node;
        return this;
    }
}
