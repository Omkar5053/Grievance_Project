package com.cutm.erp.grievance.repository;

import com.cutm.erp.grievance.entity.GrievanceAction;
import com.cutm.erp.grievance.entity.GrievanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrievanceActionRepository extends JpaRepository<GrievanceAction,Integer> {

    public List<GrievanceAction> findGrievanceActionsByGrievanceStatus(GrievanceStatus grievanceStatus);

    public List<GrievanceAction> findGrievanceActionsByActionByIsAndGrievanceStatus(int assigneeId,GrievanceStatus grievanceStatus);

    public List<GrievanceAction> findGrievanceActionsByGrievance_GrievanceId(int grievanceId);

    public Optional<GrievanceAction> findGrievanceActionByGrievance_GrievanceIdAndGrievanceStatus(int grievanceId,GrievanceStatus grievanceStatus);
}
