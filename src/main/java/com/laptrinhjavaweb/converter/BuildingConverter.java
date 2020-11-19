package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enums.DistrictEnum;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BuildingConverter {
    ModelMapper modelMapper = new ModelMapper();

    public BuildingDTO convertToDto(BuildingEntity buildingEntity) {
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        String address = "";
        for (DistrictEnum districtEnum : DistrictEnum.values()) {
            if (districtEnum.name().equals(buildingDTO.getDistrict())) {
                address = buildingDTO.getStreet() + " - " + buildingDTO.getWard() + " - " + districtEnum.getValue();
            }
        }

        buildingDTO.setAddress(address);
        return buildingDTO;
    }

    public BuildingEntity convertToEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        return buildingEntity;
    }

}
