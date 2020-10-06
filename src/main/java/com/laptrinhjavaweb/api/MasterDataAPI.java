package com.laptrinhjavaweb.api;

import com.laptrinhjavaweb.dto.output.MasterDataOutput;
import com.laptrinhjavaweb.util.MasterDataUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MasterDataAPI {

    @GetMapping("/masterdata/{code}")
    public List<MasterDataOutput> getMasterData(@PathVariable("code") String code) {
        return MasterDataUtil.of(code).getMasterData();
    }


}
