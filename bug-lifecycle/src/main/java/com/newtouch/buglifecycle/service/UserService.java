package com.newtouch.buglifecycle.service;

import com.newtouch.buglifecycle.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    void insert(User user);
}
