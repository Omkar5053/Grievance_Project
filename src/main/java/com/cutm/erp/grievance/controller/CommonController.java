package com.cutm.erp.grievance.controller;

import com.cutm.erp.common.ValueNameDto;
import com.cutm.erp.grievance.Exception.GrievanceException;
import com.cutm.erp.grievance.entity.Campus;
import com.cutm.erp.grievance.entity.Category;
import com.cutm.erp.grievance.entity.User;
import com.cutm.erp.grievance.service.AssigneeService;
import com.cutm.erp.grievance.service.CampusService;
import com.cutm.erp.grievance.service.CategoryService;
import com.cutm.erp.grievance.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/common/")
public class CommonController {

    private  final CampusService campusService;
    private  final AssigneeService assigneeService;
    private  final CategoryService categoryService;
    private  final UserService userService;

    public CommonController(CampusService campusService,
                            AssigneeService assigneeService,
                            CategoryService categoryService,
                            UserService userService
    ) {
        this.campusService = campusService;
        this.assigneeService = assigneeService;
        this.categoryService = categoryService;
        this.userService = userService;
    }


    @PostMapping(value="campusList")
    public @ResponseBody
    List<ValueNameDto> getModuleList() throws GrievanceException {
        List<Campus> campusList = campusService.listAllCampuses();
        List<ValueNameDto> dtos = new ArrayList<>();
        dtos.add( new ValueNameDto("0","Select"));
        for (Campus campus:campusList){
            dtos.add(new ValueNameDto(String.valueOf(campus.getCampusId()), campus.getCampusName()));
        }
        return dtos;
    }

    @PostMapping(value="categoryList")
    public @ResponseBody
    List<ValueNameDto> getAssigneeCategories() throws GrievanceException {
        List<Category> categoryList = categoryService.listCategories();
        List<ValueNameDto> dtos = new ArrayList<>();
        dtos.add( new ValueNameDto("0","Select"));
        for (Category category:categoryList){
            dtos.add(new ValueNameDto(String.valueOf(category.getCategoryId()), category.getCategoryType()));
        }
        return dtos;
    }

    @PostMapping(value="userList")
    public @ResponseBody
    List<ValueNameDto> getUsers() throws GrievanceException {
        List<User> userList = userService.listUsers();
        List<ValueNameDto> dtos = new ArrayList<>();
        dtos.add( new ValueNameDto("0","Select"));
        for (User user:userList){
            dtos.add(new ValueNameDto(String.valueOf(user.getUserId()), user.getFirstName()));
        }
        return dtos;
    }
}
