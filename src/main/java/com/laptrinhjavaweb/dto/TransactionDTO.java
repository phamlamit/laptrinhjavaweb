package com.laptrinhjavaweb.dto;

import java.util.Date;

public class TransactionDTO {
    private Long id;
    private String code;
    private String note;
    private Long customerId;
    private Date createdDate;
    private Date modifiedDate;
    private String createdBy;
    private String modifiedBy;

    public Long getId() {
        return id;
    }

    public TransactionDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public TransactionDTO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getNote() {
        return note;
    }

    public TransactionDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public TransactionDTO setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public TransactionDTO setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public TransactionDTO setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TransactionDTO setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public TransactionDTO setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }
}
