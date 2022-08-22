
package com.cutm.erp.grievance.controller;

import com.cutm.erp.common.ValueNameDto;
import com.cutm.erp.grievance.Exception.GrievanceException;
import com.cutm.erp.grievance.entity.Category;
import com.cutm.erp.grievance.service.CategoryService;
import com.cutm.erp.role.entity.Module;
import com.cutm.erp.role.entity.ModuleDto;
import com.cutm.erp.role.entity.Operation;
import com.cutm.erp.role.entity.RoleUtil;
import com.cutm.erp.role.service.PrivilegeService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/category/")
@Module("Category")
public class CategoryController {

    private CategoryService categoryService;
    private PrivilegeService privilegeService;

    private ValueNameDto SUCCESS = new ValueNameDto("result", "SUCCESS");
    private ValueNameDto FAILURE = new ValueNameDto("result", "FAILURE");

    public CategoryController(CategoryService categoryService, PrivilegeService privilegeService) {
        this.categoryService = categoryService;
        this.privilegeService = privilegeService;
    }

    @Operation(value="List", itemLevel=false)
    @PostMapping("list")
    public List<Category> listAllCategory(HttpServletRequest request)throws GrievanceException {
        List<Category> categories = categoryService.listCategories();
        Integer userId = (Integer) request.getSession().getAttribute(RoleUtil.ERP_SESSION_COOKIE);
        ModuleDto module = RoleUtil.getModule("Category");
        privilegeService.addActions(categories, module, userId);
        return categories;
    }

    @PostMapping("get")
    public @ResponseBody Category getCategory(@RequestParam int id)throws GrievanceException {
            return categoryService.getCategoryById(id);
    }

    @Operation(value="Add", itemLevel=false)
    @PostMapping("add")
    public @ResponseBody Category addCategory(@RequestBody Category category
    )throws GrievanceException {
        return categoryService.addCategory(category);
    }

    @Operation("Update")
    @PostMapping("update")
    public @ResponseBody Category updateCategory(@RequestParam(value = "categoryId") int categoryId,
                                   @RequestParam(value = "categoryType") String categoryType,
                                   @RequestParam(value = "categoryDescription") String categoryDescription
    )throws GrievanceException {
        return categoryService.updateCategory(categoryId, categoryType, categoryDescription);
    }

    @Operation("Delete")
    @PostMapping("delete")
    public @ResponseBody ValueNameDto deleteCategory(@RequestParam int categoryId)throws GrievanceException {
        categoryService.deleteCategoryById(categoryId);
        return SUCCESS;
    }
}
