
package com.cutm.erp.grievance.service;

import com.cutm.erp.grievance.Exception.GrievanceException;
import com.cutm.erp.grievance.entity.*;
import com.cutm.erp.grievance.repository.AssigneeRepository;
import com.cutm.erp.grievance.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConstants;
import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Logger;

@Service
public class AssigneeService {
    Logger logger = Logger.getLogger(AssigneeService.class.getName());

    private AssigneeRepository assigneeRepository;
    private CampusService campusService;
    private UserService userService;
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    public AssigneeService(AssigneeRepository assigneeRepository, CampusService campusService, UserService userService, CategoryService categoryService,CategoryRepository categoryRepository) {
        this.assigneeRepository = assigneeRepository;
        this.campusService = campusService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    public List<Assignee> getAssignees()throws GrievanceException {
        List<Assignee> assignees = assigneeRepository.findAll();
        addActions(assignees);
        return assignees;
    }

    public Assignee getAssigneeById(Integer id)throws GrievanceException {
        //return assigneeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Assignee Not Found"));
        Optional<Assignee> assignee = assigneeRepository.findById(id);
        if(assignee.isPresent())
            return assignee.get();
        else
            return null;
    }

    public Assignee add(String assigneeNo,int assignee_category,int campusId, int userId)throws GrievanceException {
        Assignee assignee = assigneeRepository.findAssigneeByUser_UserId(userId);
        if(assignee==null)
        {
            Assignee assignee1 = new Assignee();
            assignee1.setAssigneeNo(assigneeNo);
            Campus campus = campusService.getByCampusId(campusId);
            assignee1.setCampus(campus);
            User user = userService.getUserById(userId);
            assignee1.setUser(user);
            Category category = categoryService.getCategoryById(assignee_category);
            assignee1.setAssigneeCategories(List.of(category));
            return assigneeRepository.save(assignee1);
        }
       throw new GrievanceException("Assignee already exists");
    }


    public Assignee update(int assigneeId,int assignee_category, String assigneeNo, int campusId, int userId)throws GrievanceException {
            Assignee assignee = getAssigneeById(assigneeId);
            assignee.setAssigneeNo(assigneeNo);
            Campus campus = campusService.getByCampusId(campusId);
            assignee.setCampus(campus);
            User user = userService.getUserById(userId);
            assignee.setUser(user);
            Category category = categoryService.getCategoryByAssigneeId(assignee_category,assigneeId);
            List<Category> set = new ArrayList();
            set.add(category);
            assignee.setAssigneeCategories(set);
            return assigneeRepository.save(assignee);
    }

    public void deleteAssigneeById(Integer id)throws GrievanceException {
        assigneeRepository.deleteById(id);
    }


    public Assignee setCategories(String assigneeCategoryIds, Integer assigneeId)throws GrievanceException {
        Assignee assignee = getAssigneeById(assigneeId);


        List<Category> category = new ArrayList<>();
        String[] id = assigneeCategoryIds.split(",");

        for(String categoryId: id)
        {
            if(categoryId != null){
                Category categoryData = categoryRepository.findCategoryByCategoryId(Integer.parseInt(categoryId));
                if (categoryData != null) {
                    category.add(categoryData);
                    assignee.setAssigneeCategories(category);
                    assigneeRepository.save(assignee);
                }
            }
        }
        return assignee;
    }

    private void addActions(List<Assignee> assignees)throws GrievanceException {
        for (Assignee assignee : assignees) {
            assignee.getActions().add("EDIT");
            assignee.getActions().add("DELETE");
            assignee.getActions().add("CATEGORY");
        }
    }

}