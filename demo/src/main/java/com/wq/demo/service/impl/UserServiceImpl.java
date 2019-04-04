package com.wq.demo.service.impl;

import com.wq.demo.dao.UserMapper;
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
    public int selectTokenInfo(String client_open_id, String client_secret){
        return userMapper.selectTokenInfo(client_open_id,client_secret);
    }

    @Override
    public int selectId(String username, String password){
        return userMapper.selectId(username,password);
    }
}
