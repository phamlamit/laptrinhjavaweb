package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> getCustomers(CustomerDTO customerDTO);
}
