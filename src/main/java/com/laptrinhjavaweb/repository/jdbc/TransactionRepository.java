package com.laptrinhjavaweb.repository.jdbc;

import com.laptrinhjavaweb.entity.TransactionEntity;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity> {

    List<TransactionEntity> fillTransaction(Long customerId, String code);
}
