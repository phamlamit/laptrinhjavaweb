package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IUserService userService;


    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView getCuscomers(@ModelAttribute CustomerDTO customerDTO) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("modelSearch", customerDTO);
        mav.addObject("staffMaps", userService.getStaffMaps());
        mav.addObject("customers",customerService.getCustomers(customerDTO));
        return mav;
    }
}
