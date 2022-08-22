package com.cutm.erp.grievance.controller;

import com.cutm.erp.grievance.Exception.GrievanceException;
import com.cutm.erp.grievance.entity.User;
import com.cutm.erp.grievance.entity.UserType;
import com.cutm.erp.grievance.service.UserService;
import com.cutm.erp.role.entity.Module;
import com.cutm.erp.role.entity.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
@Module("User")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(value="Add", itemLevel=false)
    @PostMapping("add")
    public @ResponseBody User addUser(@RequestBody User user)throws GrievanceException
    {
        return userService.add(user);
    }

    @Operation("Search")
    @PostMapping("search")
    public @ResponseBody List<User> search(@RequestParam (required = false) String firstName,
                                                    @RequestParam (required = false) String lastName,
                                                    @RequestParam (required = false) UserType userType)throws GrievanceException{
        return userService.findUsersByFirstNameOrLastNameOrUserType(firstName, lastName,userType);
    }

}
