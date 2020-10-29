package com.cyj.jwt_test.controller;

import com.cyj.jwt_test.entity.Resp;
import com.cyj.jwt_test.entity.User;
import com.cyj.jwt_test.service.UserService;
import com.cyj.jwt_test.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/test/login")
    public Map<String, Object> Login(@RequestBody User user) {
        log.info("用户名:[{}]", user.getUsername());
        log.info("密码:[{}]", user.getPassword());
        Map<String, Object> map = new HashMap<>();
        try {
            User userDB = userService.login(user);
            Map<String, String> payload = new HashMap<>();
            payload.put("username", userDB.getUsername());
            payload.put("userid", userDB.getUserid().toString());
            String token = JWTUtil.getToken(payload);
            map.put("code", 200);
            map.put("msg", "登录成功");
            map.put("token", token);
        } catch (Exception e) {
            map.put("code", 505);
            map.put("msg", "登录失败");
        }
        return map;
    }

    @PostMapping("/user/add")
    public Map<String, Object> AddUser(@RequestBody User user) {
        log.info("用户名:[{}]", user.getUsername());
        log.info("密码:[{}]", user.getPassword());
        Map<String, Object> map = new HashMap<>();
        try {
            userService.addUser(user);
            map.put("code", 200);
            map.put("msg", "添加用户成功");
        } catch (Exception e) {
            map.put("code", 505);
            map.put("msg", "添加失败");
        }
        return map;
    }
}
