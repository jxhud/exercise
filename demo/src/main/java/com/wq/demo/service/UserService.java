package com.wq.demo.service;

import com.wq.demo.entity.UserInfo;

public interface UserService {
    public UserInfo selectUserInfo(int id);
    public int selectTokenInfo(String client_open_id, String client_secret);
    public int selectId(String username, String password);
}
