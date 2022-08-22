package com.cutm.erp.grievance.controller;

import com.cutm.erp.common.ValueNameDto;
import com.cutm.erp.grievance.Exception.GrievanceException;
import com.cutm.erp.grievance.dto.GrievanceDto;
import com.cutm.erp.grievance.entity.Grievance;
import com.cutm.erp.grievance.entity.GrievanceAction;
import com.cutm.erp.grievance.entity.GrievanceStatus;
import com.cutm.erp.grievance.service.GrievanceService;
import com.cutm.erp.role.entity.Module;
import com.cutm.erp.role.entity.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/grievance/")
@Module("Grievance")
public class GrievanceController {

    private GrievanceService grievanceService;

    public GrievanceController(GrievanceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    private ValueNameDto SUCCESS = new ValueNameDto("result", "SUCCESS");
    private ValueNameDto FAILURE = new ValueNameDto("result", "FAILURE");


    @PostMapping("list")
    @Operation("List")
    public List<GrievanceDto> grievanceList()throws GrievanceException {
        return grievanceService.grievanceList();
    }


    @PostMapping(value="statusList")
    public @ResponseBody List<ValueNameDto> getStatusList() {
        List<ValueNameDto> dtos = new ArrayList<>();
        for (GrievanceStatus status : GrievanceStatus.values()) {
            dtos.add(new ValueNameDto(status.name(), status.name()));
        }
        return dtos;
    }

    @Operation("View")
    @PostMapping("get")
    public @ResponseBody Grievance getGrievance(@RequestParam(value = "id") int id) throws GrievanceException{
        return grievanceService.getGrievanceById(id);
    }

    @Operation("Delete")
    @PostMapping("delete")
    public @ResponseBody String deleteGrievance(@RequestParam(value = "grievanceId") int grievanceId)throws GrievanceException {
        return grievanceService.deleteGrievance(grievanceId);
    }

    @Operation("Search")
    @PostMapping("search")
    public @ResponseBody List<Grievance> grievances(
            @RequestParam (value = "grievanceStatus")GrievanceStatus grievanceStatus
    )throws GrievanceException
    {
        return grievanceService.listByStatus(grievanceStatus);
    }

    @Operation(value="Add", itemLevel=false)
    @PostMapping("add")
    public @ResponseBody Grievance add(@RequestParam (value = "category") int category_id,
                            @RequestParam (value = "location") int location_id,
                            @RequestParam (value = "description") String description,
                            @RequestParam (required = false) byte[] attachment
    ) throws GrievanceException
    {
        int user_id = 101;
        return grievanceService.addGrievance(category_id,location_id,description,attachment,user_id);
    }

    @Operation("Update")
    @PostMapping("update")
    public @ResponseBody Grievance  updateGrievance(@RequestBody Grievance grievance)throws GrievanceException {
        return grievanceService.updateGrievance(grievance);
    }

    @Operation("Assign")
    @PostMapping("assign")
    public @ResponseBody GrievanceDto changeStatusToAssigned(
            @RequestParam (value = "grievanceId") int grievanceId,
            @RequestParam (value = "remark") String remark
    )throws GrievanceException
    {
        int userId = 101;
        return grievanceService.changeActionToAssigned(grievanceId,userId,remark);
    }

    @Operation("Escalate")
    @PostMapping("escalate")
    public @ResponseBody GrievanceDto changeStatusToEscalated(
            @RequestParam (value = "grievanceId") int grievanceId,
            @RequestParam (value = "remark") String remark
    )throws GrievanceException
    {
        int userId = 101;
        return grievanceService.changeActionToEscalated(grievanceId,userId,remark);
    }

    @PostMapping("reassign")
    public @ResponseBody GrievanceDto reassignToOther(
            @RequestParam (value = "grievanceId") int grievanceId,
            @RequestParam (value = "remark") String remark,
            @RequestParam (value = "assigneeId") int assigneeId
    )throws GrievanceException
    {
        int userId = 101;
        return grievanceService.reassign(grievanceId,assigneeId,userId,remark);
    }

    @Operation("Completed")
    @PostMapping("completed")
    public @ResponseBody GrievanceDto changeStatusToCompleted(
            @RequestParam (value = "grievanceId") int grievanceId,
            @RequestParam (value = "remark") String remark
    )throws GrievanceException
    {
        int userId = 101;
        return grievanceService.changeActionToCompleted(grievanceId,userId,remark);
    }

    @Operation("Close")
    @PostMapping("close")
    public @ResponseBody GrievanceDto changeStatusToClose(
            @RequestParam (value = "grievanceId") int grievanceId,
            @RequestParam (value = "remark") String remark
    )throws GrievanceException
    {
        int userId = 101;
        return grievanceService.changeActionToClose(grievanceId,userId,remark);
    }

}
