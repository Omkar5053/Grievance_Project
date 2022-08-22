package com.cutm.erp.grievance.service;

import com.cutm.erp.grievance.Exception.GrievanceException;
import com.cutm.erp.grievance.entity.Assignee;
import com.cutm.erp.grievance.entity.Campus;
import com.cutm.erp.grievance.entity.Location;
import com.cutm.erp.grievance.repository.CampusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampusService {
    private CampusRepository campusRepository;

    public CampusService(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    public List<Campus> listAllCampuses()throws GrievanceException {
        return campusRepository.findAll();
    }

    public Campus getByCampusId(Integer campusId)throws GrievanceException
    {
        Optional<Campus> campus = campusRepository.findById(campusId);
        if(campus.isPresent())
            return campus.get();
        else
            return null;
    }

    public Optional<Campus> getByCampusName(String name)throws GrievanceException
    {
        return campusRepository.findCampusByCampusName(name);
    }

    public Campus add(String campusName)throws GrievanceException
    {
        Campus campus = new Campus();
        Optional<Campus> campuses = getByCampusName(campusName);
        if(campuses.isPresent())
        {
            throw new IllegalArgumentException("Campus Already exists");
        }
        else
        {
            campus.setCampusName(campusName);
            return campusRepository.save(campus);
        }
    }

}
