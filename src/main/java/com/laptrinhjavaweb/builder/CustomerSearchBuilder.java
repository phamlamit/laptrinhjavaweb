package com.laptrinhjavaweb.builder;

public class CustomerSearchBuilder {
    private String fullname;
    private String phone;
    private String email;
    private String nameStaffIncharge;

    private CustomerSearchBuilder(CustomerSearchBuilder.Builder builder) {
        this.fullname = builder.fullname;
        this.email = builder.email;
        this.phone = builder.phone;
        this.nameStaffIncharge = builder.nameStaffIncharge;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getNameStaffIncharge() {
        return nameStaffIncharge;
    }

    public static class Builder {
        private String fullname;
        private String phone;
        private String email;
        private String nameStaffIncharge;

        public CustomerSearchBuilder build() {
            return new CustomerSearchBuilder(this);
        }

        public Builder setFullname(String fullname) {
            this.fullname = fullname;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setNameStaffIncharge(String nameStaffIncharge) {
            this.nameStaffIncharge = nameStaffIncharge;
            return this;
        }
    }
}
