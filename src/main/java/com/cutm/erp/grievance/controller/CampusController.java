package com.cutm.erp.grievance.controller;


import com.cutm.erp.grievance.Exception.GrievanceException;
import com.cutm.erp.grievance.entity.Campus;

import com.cutm.erp.grievance.service.CampusService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campus/")
public class CampusController {

    private CampusService campusService;

    public CampusController(CampusService campusService) {
        this.campusService = campusService;
    }


    @PostMapping("list")
    public List<Campus> campusList()throws GrievanceException {
        return campusService.listAllCampuses();
    }

   @PostMapping("get")
    public @ResponseBody Campus campusListById(@RequestParam (value = "campusId") int campusId)throws GrievanceException {
        return campusService.getByCampusId(campusId);
    }

    @PostMapping("add")
    public @ResponseBody Campus addCampus(@RequestParam(value = "campusName") String campusName)throws GrievanceException {
        return campusService.add(campusName);
    }

}



