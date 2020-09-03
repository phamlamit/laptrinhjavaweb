package com.laptrinhjavaweb.api;

import com.laptrinhjavaweb.dto.output.DistrictOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DistrictAPI {
    @GetMapping("/districts")
    public List<DistrictOutput> getDistrict() {
        DistrictOutput districtOutput = new DistrictOutput();
        List<DistrictOutput> result = districtOutput.getDistrictOutput();
        return result;
    }
}

