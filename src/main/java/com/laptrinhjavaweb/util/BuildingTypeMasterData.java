package com.laptrinhjavaweb.util;


import com.laptrinhjavaweb.dto.output.MasterDataOutput;
import com.laptrinhjavaweb.enums.BuildingTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class BuildingTypeMasterData implements MasterDataUtil {


    @Override
    public List<MasterDataOutput> getMasterData() {
        List<MasterDataOutput> outputs = new ArrayList<>();
        for (BuildingTypeEnum buildingTypeEnum : BuildingTypeEnum.values()) {
            MasterDataOutput masterDataOutput = new MasterDataOutput();
            masterDataOutput.setCode(buildingTypeEnum.name());
            masterDataOutput.setName(buildingTypeEnum.getTypeBuilding());
            outputs.add(masterDataOutput);
        }
        return outputs;
    }
}
