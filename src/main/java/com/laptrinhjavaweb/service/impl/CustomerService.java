package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.converter.TransactionConverter;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.AssignmentCustomerDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionConverter transactionConverter;

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

    @Override
    public CustomerDTO findById(Long id) {
        CustomerEntity customerEntity = customerRepository.findOne(id);
        CustomerDTO result = customerConverter.convertToDTO(customerEntity);
        return result;
    }

    @Override
    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
        if (customerDTO.getId() != null && !(findById(customerDTO.getId()) == null)) {
            CustomerDTO oldCustomer = findById(customerDTO.getId());
            customerEntity.setCreatedBy(oldCustomer.getCreatedBy());
            customerEntity.setCreatedDate(oldCustomer.getCreatedDate());
        }
        CustomerDTO result = customerConverter.convertToDTO(customerRepository.save(customerEntity));
        return result;
    }

    @Override
    public List<UserDTO> getAssignmentCustomer(Long id) {
        List<UserEntity> userEntityList = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserDTO> result = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            UserDTO userDTO = userConverter.convertToDto(userEntity);
            userDTO.setChecked(isAssignmentCustomer(userEntity, id));
            result.add(userDTO);
        }
        return result;
    }

    @Override
    @Transactional
    public void updateAssignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO) {
        Long[] userIds = assignmentCustomerDTO.getStaffId();
        List<UserEntity> userEntityList = new ArrayList<>();
        for (Long userId : userIds) {
            UserEntity userEntity = userRepository.findOne(userId);
            userEntityList.add(userEntity);
        }
        CustomerEntity customerEntity = customerRepository.findOne(assignmentCustomerDTO.getCustomerId());
        customerEntity.setStaffs(userEntityList);
        customerRepository.save(customerEntity);
    }

    @Override
    @Transactional
    public void delete(long[] listId) {
        for (long customerId : listId) {
            CustomerEntity customerEntity = customerRepository.findOne(customerId);
            List<TransactionEntity> transactionEntityList = transactionRepository.findByCustomerId(customerId);
            transactionRepository.delete(transactionEntityList);
            customerRepository.delete(customerEntity);
        }
    }

    @Override
    public List<TransactionDTO> getTransactions(Long id, String code) {
        List<TransactionEntity> transactionEntityList = transactionRepository.findByCodeAndCustomer_Id(code, id);
        List<TransactionDTO> result = new ArrayList<>();
        for (TransactionEntity transactionEntity : transactionEntityList) {
            TransactionDTO transactionDTO = transactionConverter.convertToDTO(transactionEntity);
            result.add(transactionDTO);
        }
        return result;
    }

    @Override
    @Transactional
    public void saveTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = transactionConverter.convertToEntity(transactionDTO);
        CustomerEntity customerEntity = customerRepository.findOne(transactionDTO.getCustomerId());
        transactionEntity.setCustomer(customerEntity);
        transactionRepository.save(transactionEntity);
    }

    public Boolean isAssignmentCustomer(UserEntity userEntity, Long customerId) {
        UserEntity userEntityChecked = userRepository.findByIdIsAndCustomers_IdIs(userEntity.getId(), customerId);
        if (userEntityChecked != null && userEntityChecked.getId() == userEntity.getId()) {
            return true;
        }
        return false;
    }
}
