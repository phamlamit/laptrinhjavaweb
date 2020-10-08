package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.entity.TransactionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    ModelMapper modelMapper = new ModelMapper();

    public TransactionDTO convertToDto(TransactionEntity transactionEntity) {
        TransactionDTO transactionDTO = modelMapper.map(transactionEntity, TransactionDTO.class);
        return transactionDTO;
    }

    public TransactionEntity convertEntity(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = modelMapper.map(transactionDTO, TransactionEntity.class);
        return transactionEntity;
    }
}
