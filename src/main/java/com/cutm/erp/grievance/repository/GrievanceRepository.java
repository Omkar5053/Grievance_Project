package com.cutm.erp.grievance.repository;

import com.cutm.erp.grievance.entity.Grievance;
import com.cutm.erp.grievance.entity.GrievanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrievanceRepository extends JpaRepository<Grievance,Integer> {
    public  List<Grievance> findGrievanceByGrievanceStatus(GrievanceStatus status);
}
