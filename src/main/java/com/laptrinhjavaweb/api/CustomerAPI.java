package com.laptrinhjavaweb.api;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.input.TransactionInput;
import com.laptrinhjavaweb.service.CustomerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerAPI {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerDTO> getCustomers() {
        List<CustomerDTO> result = customerService.fillALl();
        return result;
    }
    public List<CustomerDTO> search(@RequestParam(required = true) Map<String, String> requestParams) {
        CustomerSearchBuilder builder = convertMaptoBuilder(requestParams);
        return customerService.getCustomer(builder);
    }

    private CustomerSearchBuilder convertMaptoBuilder(Map<String, String> requestParams) {
        String fullname = requestParams.containsKey("fullname")
                ? (StringUtils.isNotBlank(requestParams.get("fullname")) ? requestParams.get("fullname")
                : null)
                : null;
        String phone = requestParams.containsKey("phone")
                ? (StringUtils.isNotBlank(requestParams.get("phone")) ? requestParams.get("phone")
                : null)
                : null;
        String email = requestParams.containsKey("email")
                ? (StringUtils.isNotBlank(requestParams.get("email")) ? requestParams.get("email")
                : null)
                : null;
        String nameStaffIncharge = requestParams.containsKey("nameStaffIncharge")
                ? (StringUtils.isNotBlank(requestParams.get("nameStaffIncharge")) ? requestParams.get("nameStaffIncharge")
                : null)
                : null;
        CustomerSearchBuilder builder = new CustomerSearchBuilder.Builder()
                .setFullname(fullname)
                .setEmail(email)
                .setPhone(phone)
                .setNameStaffIncharge(nameStaffIncharge).build();
        return builder;
    }

    @GetMapping("/customer")
    public CustomerDTO getCustomer(@RequestParam(required = true) Long id) {
        CustomerDTO result = customerService.fillById(id);
        return result;
    }

    @PostMapping("/customer/save")
    public CustomerDTO save(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO result = customerService.save(customerDTO);
        return result;
    }

    @PostMapping("/customer/update")
    public CustomerDTO update(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO result = customerService.update(customerDTO);
        return result;
    }

    @GetMapping("/customer/delete")
    public List<CustomerDTO> delete(@RequestParam(required = true) Long id) {
        List<CustomerDTO> result = customerService.delete(id);
        return result;
    }



    @GetMapping("/customer/transaction")
    public List<TransactionDTO> getTransaction(@RequestParam(required = true) Long customerId, String code){
        List<TransactionDTO> result = customerService.fillTransaction(customerId,code);
        return result;
    }
    @PostMapping("/customer/transaction/save")
    public List<TransactionDTO> save(@RequestBody TransactionInput transactionInput){
        List<TransactionDTO> result = customerService.saveTransaction(transactionInput);
        return result;
    }

}
