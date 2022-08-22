package com.cutm.erp.grievance.controller;

import com.cutm.erp.common.ValueNameDto;
import com.cutm.erp.grievance.Exception.GrievanceException;
import com.cutm.erp.grievance.entity.Assignee;
import com.cutm.erp.grievance.entity.Location;
import com.cutm.erp.grievance.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location/")
public class LocationController {


    private ValueNameDto SUCCESS = new ValueNameDto("result", "SUCCESS");
    private ValueNameDto FAILURE = new ValueNameDto("result", "FAILURE");

    private LocationService locationService;

    public LocationController(LocationService locationService)throws GrievanceException {
        this.locationService = locationService;
    }

    @PostMapping("list")
    public List<Location> getAllLocations()throws GrievanceException{
        return locationService.listLocations();
    }

    @PostMapping("get")
    public @ResponseBody Location getLocation(@RequestParam int id)throws GrievanceException{
            return locationService.getLocationById(id);
    }

    @PostMapping("add")
    public @ResponseBody Location addLocation(@RequestParam(value = "locationName") String locationName,
                                @RequestParam(value = "campusId") int campusId)throws GrievanceException{
        return locationService.addLocation(locationName,campusId);
    }


    @PostMapping("update")
    public @ResponseBody Location updateLocation(@RequestParam(value = "locationId") int locationId,
                                   @RequestParam(value = "locationName") String locationName,
                                   @RequestParam(value = "campusId") int campusId)throws GrievanceException{
        return locationService.updateLocation(locationId,locationName,campusId);
    }

    @PostMapping("delete")
    public @ResponseBody ValueNameDto deleteLocation(@RequestParam int locationId)throws GrievanceException{
        locationService.deleteLocation(locationId);
        return SUCCESS;
    }
}
