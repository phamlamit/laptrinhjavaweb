package com.laptrinhjavaweb.api;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.StaffDTO;
import com.laptrinhjavaweb.service.BuildingService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;
//	private BuildingService buildingService = new BuildingServiceImpl();

    // Create
    @PostMapping("/buildings")
    public BuildingDTO createBuildings(@RequestBody BuildingDTO buildingDTO) {
        BuildingDTO newbuilding = buildingService.save(buildingDTO);
        return newbuilding;
    }

    @GetMapping("/building")
    public BuildingDTO getBuilding(@RequestParam(required = true) String id) {
        BuildingDTO result = new BuildingDTO();
        result = buildingService.getBuildings(Long.parseLong(id));
        return result;
    }

    @GetMapping("/buildings/delete")
    public List<BuildingDTO> deleteBuilding(@RequestParam(required = true) String id) {
        List<BuildingDTO> result = buildingService.delete(Long.parseLong(id));
        return result;
    }

    @PostMapping("/buildings/update")
    public BuildingDTO updateBuilding(@RequestBody BuildingDTO buildingDTO) {
        BuildingDTO result = new BuildingDTO();
        result = buildingService.update(buildingDTO);
        return result;
    }

    @GetMapping("/assignemt-building")
    public List<StaffDTO> getUser(@RequestParam(required = true) String id) {
        List<StaffDTO> result = buildingService.fillAll(Long.parseLong(id));
        return result;
    }

    @PostMapping("/assignemt-building/update")
    public List<StaffDTO> updateUserAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        List<StaffDTO> result = buildingService.updateUserAssignmentBuilding(assignmentBuildingDTO);
        return result;
    }
    // Search nhung chua tao buildingInput
    /*
     * @PostMapping("/get-buildings") public List<BuildingDTO>
     * getBuildings(@RequestBody BuildingDTO buildingDTO){
     *
     * return null; }
     */

    @GetMapping("/buildings")
    public List<BuildingDTO> getBuildings(@RequestParam(required = true) Map<String, String> requestParams,
                                          @RequestParam("types") List<String> types) {
        BuildingSearchBuilder builder = convertMapToBuilder(requestParams);
        return buildingService.getBuildings(builder);
    }

    private BuildingSearchBuilder convertMapToBuilder(Map<String, String> requestParams) {
        Integer numberOfBasement = requestParams.containsKey("numberOfBasement")
                ? (StringUtils.isNotBlank(requestParams.get("numberOfBasement"))
                ? Integer.parseInt(requestParams.get("numberOfBasement"))
                : null)
                : null;

        Integer areaFrom = requestParams.containsKey("areaFrom")
                ? (StringUtils.isNotBlank(requestParams.get("areaFrom"))
                ? Integer.parseInt(requestParams.get("areaFrom"))
                : null)
                : null;

        Integer areaTo = requestParams.containsKey("areaTo")
                ? (StringUtils.isNotBlank(requestParams.get("areaTo")) ? Integer.parseInt(requestParams.get("areaTo"))
                : null)
                : null;

        Integer floorArea = requestParams.containsKey("floorArea")
                ? (StringUtils.isNotBlank(requestParams.get("floorArea"))
                ? Integer.parseInt(requestParams.get("floorArea"))
                : null)
                : null;

        Double rentPriceFrom = requestParams.containsKey("rentPriceFrom")
                ? (StringUtils.isNotBlank(requestParams.get("rentPriceFrom"))
                ? Double.parseDouble(requestParams.get("rentPriceFrom"))
                : null)
                : null;

        Double rentPriceTo = requestParams.containsKey("rentPriceTo")
                ? (StringUtils.isNotBlank(requestParams.get("rentPriceTo"))
                ? Double.parseDouble(requestParams.get("rentPriceTo"))
                : null)
                : null;

        BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
                .setName(requestParams.containsKey("name") ? requestParams.get("name") : null)
                .setDistrict(requestParams.containsKey("district") ? requestParams.get("district") : null)
                .setWard(requestParams.containsKey("ward") ? requestParams.get("ward") : null)
                .setNumberOfBasement(numberOfBasement).setDistrict(requestParams.get("district"))
                .setFloorArea(floorArea)
                .setStreet(requestParams.containsKey("street") ? requestParams.get("street") : null)
                .setAreaFrom(areaFrom).setAreaTo(areaTo)
                .setDirection(requestParams.containsKey("direction") ? requestParams.get("direction") : null)
                .setLevel(requestParams.containsKey("level") ? requestParams.get("level") : null)
                .setNameEmployeeInCharge(
                        requestParams.containsKey("nameEmployeeInCharge") ? requestParams.get("nameEmployeeInCharge")
                                : null)
                .setPhone(requestParams.containsKey("phone") ? requestParams.get("phone") : null)
                .setRentPriceFrom(rentPriceFrom).setRentPriceTo(rentPriceTo).build();
        return builder;
    }

}
