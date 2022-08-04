package com.team200.spyglassserver.domain.user.services;


public interface UserService {
    UserProfileDTO create(UserProfileCreateRequest detailDTO) throws ResourceCreationException;
}
