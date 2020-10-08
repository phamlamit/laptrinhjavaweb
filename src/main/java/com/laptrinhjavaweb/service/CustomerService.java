package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.input.TransactionInput;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> fillALl();

    CustomerDTO fillById(Long id);

    CustomerDTO save(CustomerDTO customerDTO);

    CustomerDTO update(CustomerDTO customerDTO);

    List<CustomerDTO> delete(Long id);

    List<CustomerDTO> getCustomer(CustomerSearchBuilder builder);

    List<TransactionDTO> fillTransaction(Long customerId, String code);

    List<TransactionDTO> saveTransaction(TransactionInput transactionInput);
}
