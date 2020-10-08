package com.laptrinhjavaweb.repository.jdbc;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity> {
    List<CustomerEntity> getCustomer(CustomerSearchBuilder builder);
}
