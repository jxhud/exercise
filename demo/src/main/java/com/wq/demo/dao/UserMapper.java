package com.wq.demo.dao;

import com.wq.demo.entity.User;
import com.wq.demo.entity.UserInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select id,username from master_user where id=#{id}")
    public UserInfo selectUserInfo(int id);

    @Insert("insert into master_user values(#{id},#{username},#{password},#{display_name})")
    public void insertUser(User user);

    @Update("update master_user set username=#{username},password=#{password},display_name=#{display_name} where id=#{id}")
    public void upateUser(User user);

    @Delete("delete master_user where id=#{id}")
    public void deleteUser(int id);
}
