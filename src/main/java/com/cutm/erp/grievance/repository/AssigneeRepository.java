package com.cutm.erp.grievance.repository;

import com.cutm.erp.grievance.entity.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssigneeRepository extends JpaRepository<Assignee,Integer> {
    public Assignee findAssigneeByUser_UserId(int user_id);
}
