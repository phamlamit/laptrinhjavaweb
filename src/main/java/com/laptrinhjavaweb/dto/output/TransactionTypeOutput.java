package com.laptrinhjavaweb.dto.output;

import com.laptrinhjavaweb.enums.TransactionTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class TransactionTypeOutput {
    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public List<TransactionTypeOutput> transactionTypeOutputList(){
        List<TransactionTypeOutput> transactionTypeOutputList = new ArrayList<TransactionTypeOutput>();
        for(TransactionTypeEnum transactionTypeEnum : TransactionTypeEnum.values()){
            TransactionTypeOutput transactionTypeOutput = new TransactionTypeOutput();
            transactionTypeOutput.setCode(transactionTypeEnum.name());
            transactionTypeOutput.setValue(transactionTypeEnum.getTypeTransaction());
            transactionTypeOutputList.add(transactionTypeOutput);
        }
        return transactionTypeOutputList;
    }
}
