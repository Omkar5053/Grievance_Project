
package com.cutm.erp.grievance.controller;

import com.cutm.erp.common.ValueNameDto;
import com.cutm.erp.grievance.Exception.GrievanceException;
import com.cutm.erp.grievance.entity.Assignee;
import com.cutm.erp.grievance.entity.Category;
import com.cutm.erp.grievance.service.AssigneeService;
import com.cutm.erp.role.controller.ModuleController;
import com.cutm.erp.role.entity.Module;
import com.cutm.erp.role.entity.ModuleDto;
import com.cutm.erp.role.entity.Operation;
import com.cutm.erp.role.entity.RoleUtil;
import com.cutm.erp.role.service.PrivilegeService;
import com.cutm.erp.role.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assignee/")
@Module("Assignee")
public class AssigneeController {


    private AssigneeService assigneeService;
    private PrivilegeService privilegeService;

    private ValueNameDto SUCCESS = new ValueNameDto("result", "SUCCESS");
    private ValueNameDto FAILURE = new ValueNameDto("result", "FAILURE");

    public AssigneeController(AssigneeService assigneeService, PrivilegeService privilegeService) {
        this.assigneeService = assigneeService;
        this.privilegeService = privilegeService;
    }

    @PostMapping("list")
    @Operation("List")
    public List<Assignee> listAllAssignee(HttpServletRequest request)throws GrievanceException {
        List<Assignee> assignees = assigneeService.getAssignees();
        Integer userId = (Integer) request.getSession().getAttribute(RoleUtil.ERP_SESSION_COOKIE);
        ModuleDto module = RoleUtil.getModule("Assignee");
        privilegeService.addActions(assignees, module, userId);
        return assignees;
    }

    @Operation("View")
    @PostMapping("get")
    public @ResponseBody Assignee getAssigneeById(@RequestParam int id)throws GrievanceException {
            return assigneeService.getAssigneeById(id);
    }

    @Operation(value="Add", itemLevel=false)
    @PostMapping("add")
    public @ResponseBody Assignee addAssignee(@RequestParam(value = "assigneeNo") String assigneeNo,
                                @RequestParam(value = "assignee_category") int assignee_category,
                                @RequestParam(value = "campusId") int campusId,
                                @RequestParam(value = "userId") int userId)throws GrievanceException {
        return assigneeService.add(assigneeNo,assignee_category,campusId, userId);
    }

    @Operation("Update")
    @PostMapping("update")
    public @ResponseBody Assignee updateAssignee(@RequestParam(value = "assigneeId") int assigneeId,
                                   @RequestParam(value = "assigneeNo") String assigneeNo,
                                   @RequestParam(value = "assignee_category") int assignee_category,
                                   @RequestParam(value = "campusId") int campusId,
                                   @RequestParam(value = "userId") Integer userId
    )throws GrievanceException {
        return assigneeService.update(assigneeId, assignee_category, assigneeNo, campusId, userId);
    }

    @PostMapping("categories")
    public @ResponseBody Assignee manageCategories(@RequestParam(value = "assigneeCategories") String assigneeCategories,
                                     @RequestParam(value = "assigneeId") int assigneeId)throws GrievanceException {
        return assigneeService.setCategories(assigneeCategories, assigneeId);
    }

    @Operation("Delete")
    @PostMapping("delete")
    public @ResponseBody ValueNameDto deleteAssignee(@RequestParam int assigneeId)throws GrievanceException {
        assigneeService.deleteAssigneeById(assigneeId);
        return SUCCESS;
    }


}