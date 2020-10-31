package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.enums.DistrictEnum;
import com.laptrinhjavaweb.service.IDistrictService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
@Service
public class DistrictService implements IDistrictService {
    @Override
    public Map<String, String> getDistricts() {
        Map<String, String> results = new LinkedHashMap<>();
        for (DistrictEnum districtEnum : DistrictEnum.values()) {
            results.put(districtEnum.name(),districtEnum.getValue());
        }
        return results;
    }
}
