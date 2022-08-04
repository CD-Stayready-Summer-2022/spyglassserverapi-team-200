package com.team200.spyglassserver.domain.user.services;

import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;
import com.team200.spyglassserver.domain.user.repo.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User getById(String id) {
        Optional<User> optional =  userRepo.findById(id);
        if(optional.isEmpty())
            throw new ResourceNotFoundException("User with id " + id + " does not exist");
       return optional.get();
    }
}
