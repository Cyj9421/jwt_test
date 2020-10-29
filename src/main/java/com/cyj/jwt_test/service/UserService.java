package com.cyj.jwt_test.service;

import com.cyj.jwt_test.entity.User;

import java.util.Map;

public interface UserService {
    public User login(User user);
    public void addUser(User user);
}
