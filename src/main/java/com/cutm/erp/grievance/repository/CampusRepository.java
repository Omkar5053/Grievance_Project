package com.cutm.erp.grievance.repository;

import com.cutm.erp.grievance.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampusRepository extends JpaRepository<Campus,Integer> {

    public Optional<Campus> findCampusByCampusName(String name);
}
