package com.newtouch.buglifecycle.service;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    void insert(User user);
}
