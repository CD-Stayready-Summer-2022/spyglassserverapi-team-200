package com.team200.spyglassserver.domain.user.services;


import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;
import com.team200.spyglassserver.domain.user.dtos.UserCreateRequest;
import com.team200.spyglassserver.domain.user.dtos.UserDTO;
import com.team200.spyglassserver.domain.user.model.User;

public interface UserService {
    UserDTO create(UserCreateRequest detailDTO) throws ResourceNotFoundException;
    UserDTO getById(String id) throws ResourceNotFoundException;
    UserDTO getByEmail(String email) throws ResourceNotFoundException;
    UserDTO update(String id, User userDetail) throws ResourceNotFoundException;
    void delete(String id);
    Iterable<UserDTO> getGoals(String id) throws ResourceNotFoundException;
    User retrieveById(String id) throws ResourceNotFoundException;

}
