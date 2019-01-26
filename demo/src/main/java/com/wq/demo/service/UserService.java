package com.wq.demo.service;

import com.wq.demo.entity.User;
import com.wq.demo.entity.UserInfo;

public interface UserService {
    public UserInfo selectUserInfo(int id);
    public void insertUser(User user);
    public void updateUser(User user);
    public void deleteUser(int id);
}
