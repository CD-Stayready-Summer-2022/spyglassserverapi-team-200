package com.team200.spyglassserver.domain.user.services;

import com.team200.spyglassserver.domain.core.exceptions.ResourceCreationException;
import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;
import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.user.dtos.UserCreateRequest;
import com.team200.spyglassserver.domain.user.dtos.UserDTO;
import com.team200.spyglassserver.domain.user.model.User;
import com.team200.spyglassserver.domain.user.repo.UserRepo;
import com.team200.spyglassserver.security.firebase.services.FirebaseUserMgrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepo UserRepo;
    private FirebaseUserMgrService FirebaseUserMgrService;
    @Autowired
    public UserServiceImpl(UserRepo UserRepo, FirebaseUserMgrService FirebaseUserMgrService){
        this.UserRepo = UserRepo;
        this.FirebaseUserMgrService = FirebaseUserMgrService;
    }
    @Override
    public User retrieveById(String id) throws ResourceNotFoundException {
        User user = UserRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(""));
        return user;
    }
    @Override
    public UserDTO create(UserCreateRequest detailDTO) throws ResourceNotFoundException {
        Optional<User> optional = UserRepo.findByEmail(detailDTO.getEmail());
        if (optional.isPresent()) {
            throw new ResourceCreationException();
        }
        log.info(detailDTO.toString());
        String uid = FirebaseUserMgrService.createFireBaseUser(detailDTO);
        User user = new User(detailDTO.getFirstName(),detailDTO.getLastName(),detailDTO.getEmail());
        user.setId(uid);
        user = UserRepo.save(user);
        log.debug("Created User with id {} and email {}", user.getId(), user.getEmail());
        return new UserDTO(user);
    }


    @Override
    public Iterable<UserDTO> getAll() {
        return null;
    }

    @Override
    public UserDTO getById(String id) throws ResourceNotFoundException {
        User User = UserRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(""));
        return new UserDTO(User);
    }
    @Override
    public UserDTO getByEmail(String email) throws ResourceNotFoundException {
        User user = UserRepo.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException(""));
        return new UserDTO(user);
    }

    @Override
    public UserDTO update(String id, User userDetail) throws ResourceNotFoundException {
        User User = retrieveById(userDetail.getId());
        FirebaseUserMgrService.updateFireBaseUser(userDetail);
        User.setEmail(userDetail.getEmail());
        User.setFirstName(userDetail.getFirstName());
        User.setLastName(userDetail.getLastName());
        User = UserRepo.save(User);
        return new UserDTO(User);
    }

    @Override
    public void delete(String id) {
        User user = retrieveById(id);
        FirebaseUserMgrService.deleteFireBaseUser(id);
        UserRepo.delete(user);
    }

    @Override
    public Iterable<UserDTO> getGoals(String id) throws ResourceNotFoundException {
        User user = retrieveById(id);
        List<Goal> goals = user.getGoals();
        //for (Goal goal:goals) {
            //userProfileDTOS.add(new UserProfileDTO(userProfile));
        return null;
        }
    }
