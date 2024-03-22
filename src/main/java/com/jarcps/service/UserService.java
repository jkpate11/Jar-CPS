package com.jarcps.service;

import com.jarcps.model.User;

public interface UserService {
    User registerUser(User user);
    User getUserById(Long id);
}
