package com.laptrinhjavaweb.dto;

public class AssignmentCustomerDTO extends BaseDTO{
    private Long customerId;
    private Long[] staffId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long[] getStaffId() {
        return staffId;
    }

    public void setStaffId(Long[] staffId) {
        this.staffId = staffId;
    }
}
