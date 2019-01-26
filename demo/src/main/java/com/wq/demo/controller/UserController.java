package com.wq.demo.controller;

import com.wq.demo.entity.User;
import com.wq.demo.entity.UserInfo;
import com.wq.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    public @ResponseBody UserInfo selectUserInfo(@PathVariable("id") int id){
        return userService.selectUserInfo(id);
    }

    @PostMapping("/user")
    public void insertUser(@RequestBody User user){
        userService.insertUser(user);
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
    }
}
