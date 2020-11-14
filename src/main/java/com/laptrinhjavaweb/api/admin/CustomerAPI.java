package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.AssignmentCustomerDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

@RestController(value = "customerAPIOfAdmin")
public class CustomerAPI {
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/api/customer")
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO result = customerService.save(customerDTO);
        return result;
    }

    @GetMapping("api/customer/assignment-customer")
    public List<UserDTO> getAssignmentCustomer(@RequestParam("id") Long id) {
        List<UserDTO> result = new ArrayList<>();
        result = customerService.getAssignmentCustomer(id);
        return result;
    }

    @PutMapping("api/customer/assignment-customer")
    public void updateAssignmentCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO) {
        customerService.updateAssignmentCustomer(assignmentCustomerDTO);
    }

    @DeleteMapping("api/customer")
    public void deleteCustomer(@RequestBody long[] ids) {
        customerService.delete(ids);
    }
    @GetMapping("api/customer/transaction")
    public List<TransactionDTO> getTransaction(@RequestParam Long buildingId, String code){
        List<TransactionDTO> result = customerService.getTransactions(buildingId, code);
        return result;
    }
    @PostMapping("api/customer/transaction")
    public void saveTransaction(@RequestBody TransactionDTO transactionDTO){
        customerService.saveTransaction(transactionDTO);
    }
}
