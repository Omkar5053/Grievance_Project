package com.cutm.erp.grievance.repository;

import com.cutm.erp.grievance.entity.User;
import com.cutm.erp.grievance.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public List<User> findUsersByFirstNameOrLastNameOrUserType(String firstName, String lastName, UserType userType);
    Optional<User> findByLoginIdAndPassword(String loginId, String password);

}
