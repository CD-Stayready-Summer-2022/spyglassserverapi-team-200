package com.team200.spyglassserver.domain.user.services;

import org.apache.catalina.User;

public interface UserService {
    User getById(String id);
}
