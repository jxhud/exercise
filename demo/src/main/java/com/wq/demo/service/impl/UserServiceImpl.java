package com.wq.demo.service.impl;

import com.wq.demo.dao.UserMapper;
import com.wq.demo.entity.User;
import com.wq.demo.entity.UserInfo;
import com.wq.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo selectUserInfo(int id) {
        return userMapper.selectUserInfo(id);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.upateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }
}
