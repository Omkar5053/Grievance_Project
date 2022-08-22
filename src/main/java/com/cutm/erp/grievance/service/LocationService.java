package com.cutm.erp.grievance.service;

import com.cutm.erp.grievance.Exception.GrievanceException;
import com.cutm.erp.grievance.entity.Assignee;
import com.cutm.erp.grievance.entity.Campus;
import com.cutm.erp.grievance.entity.Category;
import com.cutm.erp.grievance.entity.Location;
import com.cutm.erp.grievance.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private LocationRepository locationRepository;
    private CampusService campusService;

    public LocationService(LocationRepository locationRepository, CampusService campusService) {
        this.locationRepository = locationRepository;
        this.campusService = campusService;
    }

    // all location
    public List<Location> listLocations()throws GrievanceException {
        List<Location> locations = locationRepository.findAll();
        addActions(locations);
        return locations;
    }

    // get location by id
    public Location getLocationById(int id)throws GrievanceException {
       // return locationRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Location Not Found"));
        Optional<Location> location = locationRepository.findById(id);
        if(location.isPresent())
            return location.get();
        else
            return null;
    }

    public Optional<Location> getByCampusIdAndLocationName(int campus_id,String name)throws GrievanceException
    {
        return locationRepository.findLocationByCampus_CampusIdAndLocationName(campus_id,name);
    }

    // add location
    public Location addLocation(String locationName, int campusId)throws GrievanceException {
        Optional<Location> location = getByCampusIdAndLocationName(campusId,locationName);
        if(location.isPresent())
        {
            throw  new IllegalArgumentException("Location already exists!!");
        }
        else
        {
            Location location1 = new Location();
            Campus campus = campusService.getByCampusId(campusId);

            location1.setLocationName(locationName);
            location1.setCampus(campus);
            return locationRepository.save(location1);
        }
    }

    // update location
    public Location updateLocation(Integer locationId, String locationName, int campusId)throws GrievanceException{
        Location location = getLocationById(locationId);
        Campus campus = campusService.getByCampusId(campusId);
        location.setLocationId(locationId);
        location.setLocationName(locationName);
        location.setCampus(campus);
        return locationRepository.save(location);
    }

    // delete location
    public void deleteLocation(int id)throws GrievanceException{
        locationRepository.deleteById(id);
    }
    // addAction
    private void addActions(List<Location> locations)throws GrievanceException {
        for (Location location : locations) {
            location.getActions().add("EDIT");
            location.getActions().add("DELETE");
        }
    }


}
