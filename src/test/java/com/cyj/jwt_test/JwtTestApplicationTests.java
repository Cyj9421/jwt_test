package com.cyj.jwt_test;

import com.cyj.jwt_test.dao.UserDao;
import com.cyj.jwt_test.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtTestApplicationTests {
    @Autowired
    private UserDao userDao;
    @Test
    void contextLoads() {
        User user=new User(2,"cyj","9421");
        User userDb=userDao.login(user);
        System.out.println(userDb);
    }

}
