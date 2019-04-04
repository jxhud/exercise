package com.wq.demo.dao;

import com.wq.demo.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select id,display_name from master_user where id=#{id}")
    public UserInfo selectUserInfo(int id);

    @Select("select ISNULL(max(id),0) from master_client where client_open_id=#{client_open_id} and client_secret=#{client_secret}")
    public int selectTokenInfo(String client_open_id, String client_secret);

    @Select("select ISNULL(max(id),0) from master_user where username=#{username} and password=#{password}")
    public int selectId(String username, String password);
}
