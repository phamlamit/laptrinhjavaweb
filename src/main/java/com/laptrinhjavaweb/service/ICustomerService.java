package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.AssignmentCustomerDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.UserDTO;

import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> getCustomers(CustomerDTO customerDTO);

    CustomerDTO findById(Long id);

    CustomerDTO save(CustomerDTO customerDTO);

    List<UserDTO> getAssignmentCustomer(Long id);

    void updateAssignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO);

    void delete(long[] listId);

    List<TransactionDTO> getTransactions(Long buildingId, String code);

    void saveTransaction(TransactionDTO transactionDTO);
}
