package com.cutm.erp.grievance.repository;

import com.cutm.erp.grievance.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {

    public Optional<Location> findLocationByCampus_CampusIdAndLocationName(int campusId,String locationName);
}
