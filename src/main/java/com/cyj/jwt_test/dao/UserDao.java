package com.cyj.jwt_test.dao;

import com.cyj.jwt_test.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserDao {
    public User login(User user);
    public void addUser(User user);
}
