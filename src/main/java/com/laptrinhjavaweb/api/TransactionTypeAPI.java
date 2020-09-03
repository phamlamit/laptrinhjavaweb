package com.laptrinhjavaweb.api;

import com.laptrinhjavaweb.dto.output.TransactionTypeOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class TransactionTypeAPI {
    @GetMapping("/transaction-type")
    public List<TransactionTypeOutput> getTransactionType() {
        TransactionTypeOutput transactionTypeOutput = new TransactionTypeOutput();
        return transactionTypeOutput.transactionTypeOutputList();
    }
}
