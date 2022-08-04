package com.team200.spyglassserver.security.firebase.services;


import com.team200.spyglassserver.domain.core.exceptions.ResourceCreationException;
import com.team200.spyglassserver.domain.core.exceptions.ResourceUpdateException;
import com.team200.spyglassserver.domain.user.dtos.UserCreateRequest;
import com.team200.spyglassserver.domain.user.model.User;

public interface FirebaseUserMgrService {
    String createFireBaseUser(UserCreateRequest userDetail) throws ResourceCreationException;
    void updateFireBaseUser(User userDetail) throws ResourceUpdateException;
    void deleteFireBaseUser(String id) throws ResourceUpdateException;
}
