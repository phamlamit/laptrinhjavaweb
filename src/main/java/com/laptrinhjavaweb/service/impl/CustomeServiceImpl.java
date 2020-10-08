package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.converter.TransactionConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.input.TransactionInput;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.repository.jdbc.CustomerRepository;
import com.laptrinhjavaweb.repository.jdbc.TransactionRepository;
import com.laptrinhjavaweb.repository.jdbc.impl.CustomerRepositoryIml;
import com.laptrinhjavaweb.repository.jdbc.impl.TransactionRepositoryImpl;
import com.laptrinhjavaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomeServiceImpl implements CustomerService {
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private TransactionConverter transactionConverter;

    private CustomerRepository customerRepository = new CustomerRepositoryIml();

    private TransactionRepository transactionRepository = new TransactionRepositoryImpl();

    @Override
    public List<CustomerDTO> fillALl() {
        List<CustomerEntity> listCustomerEntity = customerRepository.fillAll();
        List<CustomerDTO> result = listCustomerEntity.stream().map(item -> {
            CustomerDTO customerDTO = customerConverter.convertToDto(item);
            return customerDTO;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public CustomerDTO fillById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id);
        CustomerDTO result = customerConverter.convertToDto(customerEntity);
        return result;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.convertEntity(customerDTO);
        Long id = customerRepository.save(customerEntity);
        CustomerDTO result = fillById(id);
        return result;
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.convertEntity(customerDTO);
        Long id = customerRepository.update(customerEntity);
        CustomerDTO result = fillById(id);
        return result;
    }

    @Override
    public List<CustomerDTO> delete(Long id) {
        customerRepository.delete(id);
        List<CustomerDTO> result = fillALl();
        return result;
    }

    @Override
    public List<CustomerDTO> getCustomer(CustomerSearchBuilder builder) {
        List<CustomerEntity> listCustomerEntity = customerRepository.getCustomer(builder);
        List<CustomerDTO> result = listCustomerEntity.stream().map(item -> {
            CustomerDTO customerDTO = customerConverter.convertToDto(item);
            return customerDTO;
        }).collect(Collectors.toList());
        return result;

    }

    @Override
    public List<TransactionDTO> fillTransaction(Long customerId, String code) {
        List<TransactionEntity> listTransactionEntity = transactionRepository.fillTransaction(customerId, code);
        List<TransactionDTO> result = listTransactionEntity.stream().map(item -> {
            TransactionDTO transactionDTO = transactionConverter.convertToDto(item);
            return transactionDTO;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<TransactionDTO> saveTransaction(TransactionInput transactionInput) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setCode(transactionInput.getCode());
        transactionEntity.setNote(transactionInput.getNode());
        transactionEntity.setCustomerId(transactionInput.getCustomerId());
        transactionRepository.save(transactionEntity);
        List<TransactionDTO> result = fillTransaction(transactionInput.getCustomerId(), transactionInput.getCode());
        return result;
    }
}
