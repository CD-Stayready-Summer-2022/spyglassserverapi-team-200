package com.team200.spyglassserver.domain.user.services;

import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;
import com.team200.spyglassserver.domain.user.dtos.UserCreateRequest;
import com.team200.spyglassserver.domain.user.dtos.UserDTO;
import com.team200.spyglassserver.domain.user.model.User;

public class UserServiceImpl implements UserService {


    @Override
    public UserDTO create(UserCreateRequest detailDTO) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public Iterable<UserDTO> getAll() {
        return null;
    }

    @Override
    public UserDTO getById(String id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public UserDTO getByEmail(String email) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public UserDTO update(String id, User userDetail) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Iterable<UserDTO> getGoals(String id) throws ResourceNotFoundException {
        return null;
    }
}
