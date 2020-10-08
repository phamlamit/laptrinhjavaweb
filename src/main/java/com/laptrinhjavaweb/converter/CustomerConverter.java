package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    ModelMapper modelMapper = new ModelMapper();

    public CustomerDTO convertToDto(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        return customerDTO;
    }

    public CustomerEntity convertEntity(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        return customerEntity;
    }
}