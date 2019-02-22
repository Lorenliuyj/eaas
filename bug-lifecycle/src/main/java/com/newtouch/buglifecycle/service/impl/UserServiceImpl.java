package com.newtouch.buglifecycle.service.impl;


import com.newtouch.buglifecycle.dao.UserDao;
import com.newtouch.buglifecycle.entity.User;
import com.newtouch.buglifecycle.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);

    }
}
