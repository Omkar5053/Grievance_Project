package com.cutm.erp.grievance.repository;

import com.cutm.erp.grievance.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    public List<Category> findCategoriesByCategoryIdIn(List<Integer> categoriesIds);
    public List<Category> findCategoriesByCategoryIdInAndAssignee_AssigneeId(List<Integer> categoriesIds, int assigneeId);
    public Optional<Category> findCategoryByCategoryType(String categoryType);
    public Category findCategoryByCategoryIdAndAssignee_AssigneeId(int categoryId,int assigneeId);

    @Query("from Category as c where c.categoryId = :categoryId")
    public Category findCategoryByCategoryId(int categoryId);
}
