package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;

    @Override
    public List<CustomerDTO> getCustomers(CustomerDTO customerDTO) {
        List<CustomerEntity> customerEntityList = customerRepository.getCustomers(customerDTO);
        List<CustomerDTO> result = new ArrayList<>();
        for (CustomerEntity customerEntity : customerEntityList) {
            CustomerDTO customer = customerConverter.convertToDTO(customerEntity);
            result.add(customer);
        }
        return result;
    }
}
