package com.laptrinhjavaweb.util;

import com.laptrinhjavaweb.dto.output.MasterDataOutput;
import com.laptrinhjavaweb.enums.TransactionTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class TransactionTypeMasterData implements MasterDataUtil {
    @Override
    public List<MasterDataOutput> getMasterData() {
        List<MasterDataOutput> outputs = new ArrayList<MasterDataOutput>();
        for (TransactionTypeEnum transactionTypeEnum : TransactionTypeEnum.values()) {
            MasterDataOutput masterDataOutput = new MasterDataOutput();
            masterDataOutput.setCode(transactionTypeEnum.name());
            masterDataOutput.setName(transactionTypeEnum.getTypeTransaction());
            outputs.add(masterDataOutput);
        }
        return outputs;
    }
}
