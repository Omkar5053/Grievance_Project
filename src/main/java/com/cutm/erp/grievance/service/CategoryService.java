package com.cutm.erp.grievance.service;

import com.cutm.erp.grievance.Exception.GrievanceException;
import com.cutm.erp.grievance.entity.Assignee;
import com.cutm.erp.grievance.entity.Campus;
import com.cutm.erp.grievance.entity.Category;
import com.cutm.erp.grievance.repository.AssigneeRepository;
import com.cutm.erp.grievance.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;


@Service
public class CategoryService {

    Logger logger = Logger.getLogger(CategoryService.class.getName());

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Get All Category
    public List<Category> listCategories()throws GrievanceException {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    // Get Category By Id
    public Category getCategoryById(Integer id)throws GrievanceException {
        //return categoryRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Category Not Found"));
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent())
            return category.get();
        else
            return null;
    }

    public List<Category> getByCategoriesIds(List<Integer> ids)throws GrievanceException {
        return categoryRepository.findCategoriesByCategoryIdIn(ids);
    }

    public List<Category> findCategoriesByAssigneeId(String ids, int assigneeId)throws GrievanceException {
        logger.info("List:" + ids);
        List<Integer> category = new ArrayList<>();
        String[] id = ids.split(",");
        for(String categoryId: id)
        {
            if(categoryId != null){
                Category categoryData = categoryRepository.getById(Integer.parseInt(categoryId));
                if(categoryData != null) {
                    category.add(categoryData.getCategoryId());
                }
            }
        }
        logger.info("CategoryId:" + category);
        return categoryRepository.findCategoriesByCategoryIdInAndAssignee_AssigneeId(category,assigneeId);
    }

    public Category getCategoryByAssigneeId(int categoryId,int assigneeId)
    {
        return categoryRepository.findCategoryByCategoryIdAndAssignee_AssigneeId(categoryId,assigneeId);
    }

    // Add Category
    public Category addCategory(Category category1)throws GrievanceException {
        Optional<Category> category = categoryRepository.findCategoryByCategoryType(category1.getCategoryType());
        if (category.isPresent()) {
            throw new GrievanceException("Category already exists");
        } else {
            Category category2 = new Category();
            category2.setCategoryType(category1.getCategoryType());
            category2.setCategoryDescription(category1.getCategoryDescription());
            return categoryRepository.save(category2);
        }
    }

    public Category updateCategory(int categoryId, String categoryType, String categoryDescription)throws GrievanceException {

        Category category = getCategoryById(categoryId);
        category.setCategoryType(categoryType);
        category.setCategoryDescription(categoryDescription);
        return categoryRepository.save(category);
    }

    // Delete Category
    public void deleteCategoryById(Integer id)throws GrievanceException {
        categoryRepository.deleteById(id);
    }

    // addAction
    private void addActions(List<Category> categories)throws GrievanceException {
        for (Category category : categories) {
            category.getActions().add("EDIT");
            category.getActions().add("DELETE");
        }
    }
}
