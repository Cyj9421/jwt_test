package com.cyj.jwt_test.service.serviceimpl;

import com.cyj.jwt_test.dao.UserDao;
import com.cyj.jwt_test.entity.User;
import com.cyj.jwt_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User login(User user) {
        User user1=userDao.login(user);
        return user1;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
}
