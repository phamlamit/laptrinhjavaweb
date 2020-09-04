package com.laptrinhjavaweb.dto.output;

import com.laptrinhjavaweb.enums.DistrictEnum;

import java.util.ArrayList;
import java.util.List;

public class DistrictOutput {
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


    public List<DistrictOutput> listDistrictOutput() {
        List<DistrictOutput> districtOutputList = new ArrayList<DistrictOutput>();
        for (DistrictEnum districtEnum : DistrictEnum.values()) {
            DistrictOutput districtOutput = new DistrictOutput();
            districtOutput.setCode(districtEnum.name());
            districtOutput.setValue(districtEnum.getDistrict());
            districtOutputList.add(districtOutput);
        }
        return districtOutputList;
    }
}
