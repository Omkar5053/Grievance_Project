package com.cutm.erp.grievance.entity;

import com.cutm.erp.common.BaseDto;
import javax.persistence.*;
import java.util.List;
import org.springframework.lang.Nullable;

@Entity
public class Assignee extends BaseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assigneeId;
    @Column(length = 15,unique = true)
    private String assigneeNo;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "assignee_category",
               joinColumns = @JoinColumn(name = "assignee_id"),
               inverseJoinColumns = @JoinColumn(name = "assignee_categories_category_id"))
    private List<Category> assigneeCategories;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "campus_id")
    private Campus campus;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Assignee() {
    }

    public Assignee(int assigneeId, String assigneeNo, List<Category> assigneeCategories, @Nullable Campus campus, User user) {
        this.assigneeId = assigneeId;
        this.assigneeNo = assigneeNo;
        this.assigneeCategories = assigneeCategories;
        this.campus = campus;
        this.user = user;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getAssigneeNo() {
        return assigneeNo;
    }

    public void setAssigneeNo(String assigneeNo) {
        this.assigneeNo = assigneeNo;
    }

    public List<Category> getAssigneeCategories() {
        return assigneeCategories;
    }

    public void setAssigneeCategories(List<Category> assigneeCategories) {
        this.assigneeCategories = assigneeCategories;
    }

    @Nullable
    public Campus getCampus() {
        return campus;
    }

    public void setCampus(@Nullable Campus campus) {
        this.campus = campus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Assignee{" +
                "assigneeId=" + assigneeId +
                ", assigneeNo='" + assigneeNo + '\'' +
                ", assigneeCategories=" + assigneeCategories +
                ", campus=" + campus +
                ", user=" + user +
                '}';
    }
}
