package com.newtouch.buglifecycle.dao;


import com.newtouch.buglifecycle.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUser();
    void insert(User user);

}
