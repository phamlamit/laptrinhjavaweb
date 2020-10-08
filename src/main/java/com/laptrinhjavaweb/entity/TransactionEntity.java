package com.laptrinhjavaweb.entity;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;
import com.laptrinhjavaweb.annotation.Table;

import java.util.Date;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Column(name = "id")
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "note")
    private String note;
    @Column(name = "customerid")
    private Long customerId;
    @Column(name = "createddate")
    private Date createdDate;
    @Column(name = "modifieddate")
    private Date modifiedDate;
    @Column(name = "createdby")
    private String createdBy;
    @Column(name = "modifiedby")
    private String modifiedBy;

    public Long getId() {
        return id;
    }

    public TransactionEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public TransactionEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public String getNote() {
        return note;
    }

    public TransactionEntity setNote(String note) {
        this.note = note;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public TransactionEntity setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public TransactionEntity setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public TransactionEntity setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TransactionEntity setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public TransactionEntity setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }
}
