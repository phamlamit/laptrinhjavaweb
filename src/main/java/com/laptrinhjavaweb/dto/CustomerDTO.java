package com.laptrinhjavaweb.dto;

import java.util.Date;

public class CustomerDTO {
    private Long id;
    private String fullname;
    private String phone;
    private String email;
    private Date createdDate;
    private Date modifiedDate;
    private String createdBy;
    private String modifiedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public CustomerDTO setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public CustomerDTO setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
