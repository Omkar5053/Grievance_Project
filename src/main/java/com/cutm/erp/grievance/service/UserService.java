package com.cutm.erp.grievance.service;

import com.cutm.erp.grievance.Exception.GrievanceException;
import com.cutm.erp.grievance.entity.User;
import com.cutm.erp.grievance.entity.UserType;
import com.cutm.erp.grievance.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public User getUserById(Integer userId)throws GrievanceException{
        return userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("User Not Found"));
    }

    public User add(User user)throws GrievanceException
    {
        return userRepository.save(user);
    }

    public UserService(UserRepository userRepository)throws GrievanceException {
        this.userRepository = userRepository;
    }


    public List<User> findUsersByFirstNameOrLastNameOrUserType(String firstName, String lastName, UserType userType)throws GrievanceException{
        return userRepository.findUsersByFirstNameOrLastNameOrUserType(firstName,lastName,userType);
    }

    //for login
    public User authenticate(String login, String password) throws GrievanceException {
        Optional<User> user = userRepository.findByLoginIdAndPassword(login, password);
        if (user.isEmpty())
            throw new GrievanceException("User not found");
        return user.get();
    }

    public User getUser(String userIdStr) throws GrievanceException {
        int userId = Integer.parseInt(userIdStr);
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new GrievanceException("User not found");
        return user.get();
    }

    public List<User> listUsers()
    {
        return userRepository.findAll();
    }
}
