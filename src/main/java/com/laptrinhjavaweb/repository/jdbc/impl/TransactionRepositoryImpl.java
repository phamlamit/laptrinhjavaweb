package com.laptrinhjavaweb.repository.jdbc.impl;

import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.repository.jdbc.TransactionRepository;

import java.util.List;

public class TransactionRepositoryImpl extends SimpleJpaRepositoryImpl<TransactionEntity> implements TransactionRepository {

    @Override
    public List<TransactionEntity> fillTransaction(Long customerId, String code) {
        String sql = "SELECT * FROM transaction WHERE customerid = " + customerId + " and code = '" + code + "'";
        List<TransactionEntity> result = fillAll(sql);
        return result;
    }
}
