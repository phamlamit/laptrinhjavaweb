package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.ITransactionTypeService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITransactionTypeService transactionTypeService;

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView getCuscomers(@ModelAttribute CustomerDTO customerDTO) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("modelSearch", customerDTO);
        mav.addObject("staffMaps", userService.getStaffMaps());
        mav.addObject("customers", customerService.getCustomers(customerDTO));
        return mav;
    }

    @RequestMapping(value = "admin/customer-edit", method = RequestMethod.GET)
    public ModelAndView CustomerEdit(@ModelAttribute("model") CustomerDTO customerDTO) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("mapTransactionTypes", transactionTypeService.getTransactionType());
        if (customerDTO.getId() != null) {
            customerDTO = customerService.findById(customerDTO.getId());
            mav.addObject("model", customerDTO);
            Map<String, List<TransactionDTO>> listMap = new HashMap<>();
            for (String code : transactionTypeService.getTransactionType().keySet()) {
                listMap.put(code, customerService.getTransactions(customerDTO.getId(), code));
            }
            mav.addObject("mapTransaction", listMap);
        }
        return mav;
    }
}
