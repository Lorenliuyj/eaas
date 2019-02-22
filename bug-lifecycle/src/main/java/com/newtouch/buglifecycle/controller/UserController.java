package com.newtouch.buglifecycle.controller;

import com.newtouch.buglifecycle.entity.User;
import com.newtouch.buglifecycle.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/getAllUser")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping(value = "/insert")
    public void insert(@Param("id") int id,
                       @Param("username") String username,
                       @Param("age") String age,
                       @Param("sex") String sex){
        User user = new User();
        user.setId(id);
        user.setUserName(username);
        user.setAge(age);
        user.setSex(sex);
        userService.insert(user);
    }

}
