package com.laptrinhjavaweb.util;

import com.laptrinhjavaweb.dto.output.MasterDataOutput;

import java.util.List;

public interface MasterDataUtil {
    static MasterDataUtil of(String code) {
        if (code.equals("districts")) {
            return new DistrictMasterData();
        } else if (code.equals("buildingtype")) {
            return new BuildingTypeMasterData();
        }
        return null;
    }

    List<MasterDataOutput> getMasterData();

}
