package com.cutm.erp.grievance.entity;

import com.cutm.erp.common.BaseDto;
import com.cutm.erp.grievance.entity.Assignee;

import javax.persistence.*;

import java.util.List;

@Entity
public class Category extends BaseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(unique = true,length = 32)
    private String categoryType;

    @Column(length = 122)
    private String categoryDescription;

    @ManyToOne
    private Assignee assignee;

    public Category() {
    }

    public Category(int categoryId, String categoryType, String categoryDescription) {
        this.categoryId = categoryId;
        this.categoryType = categoryType;
        this.categoryDescription = categoryDescription;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryType='" + categoryType + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                '}';
    }
}
