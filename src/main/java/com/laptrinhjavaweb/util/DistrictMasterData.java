package com.laptrinhjavaweb.util;

import com.laptrinhjavaweb.dto.output.MasterDataOutput;
import com.laptrinhjavaweb.enums.DistrictEnum;

import java.util.ArrayList;
import java.util.List;

public class DistrictMasterData implements MasterDataUtil {

    @Override
    public List<MasterDataOutput> getMasterData() {
        List<MasterDataOutput> outputs = new ArrayList<>();
        for (DistrictEnum districtEnum : DistrictEnum.values()) {
            MasterDataOutput masterDataOutput = new MasterDataOutput();
            masterDataOutput.setCode(districtEnum.name());
            masterDataOutput.setName(districtEnum.getDistrict());
            outputs.add(masterDataOutput);
        }
        return outputs;
    }


}
