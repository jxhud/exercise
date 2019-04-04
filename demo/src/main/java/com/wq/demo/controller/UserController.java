package com.wq.demo.controller;

import com.wq.demo.entity.TokenInfo;
import com.wq.demo.entity.UserInfo;
import com.wq.demo.entity.UserMessage;
import com.wq.demo.service.TokenService;
import com.wq.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Resource
    TokenService tokenService;
    private String redirect_uri;

    @GetMapping("/user")
    public @ResponseBody
    ResponseEntity<UserInfo> selectUserInfo(@RequestParam(name = "id", required = true) int id, @RequestParam(name = "access_token", required = true) String access_token){
        if(tokenService.hasCode(access_token)){
            UserInfo userInfo= userService.selectUserInfo(id);
            HttpStatus status =  HttpStatus.OK ;
            return new ResponseEntity<UserInfo>(userInfo,status);
        }
        else {
            //UserInfo userInfo = new UserInfo(id,"error");
            UserInfo userInfo = null;
            HttpStatus status = HttpStatus.UNAUTHORIZED;
            return new ResponseEntity<UserInfo>(userInfo,status);
        }
    }

    @GetMapping("/access_token")
    public @ResponseBody ResponseEntity<TokenInfo> selectTokenInfo(@RequestParam(name = "client_id", required = true)  String client_open_id,@RequestParam(name = "client_secret", required = true) String client_secret){
        System.out.println("access_token");
        int id = userService.selectTokenInfo(client_open_id,client_secret);
        System.out.println("select ok");
        if(id == 0){
            TokenInfo tokenInfo = null;
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<TokenInfo>(tokenInfo,status);
        }
        else {
            //使用uuid作为源token
            String token = UUID.randomUUID().toString();
            //把生成的token放入redis缓存，key和value都设置为token
            System.out.println("UUID ok");
            tokenService.setCode(token,token);
            System.out.println("redis ok");
            TokenInfo tokenInfo = new TokenInfo(token,7200);
            HttpStatus status = HttpStatus.OK;
            return new ResponseEntity<TokenInfo>(tokenInfo,status);
        }
    }

    @GetMapping("/baidu")
    public String redirect(){
        return "redirect:https://www.baidu.com";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "redirect_uri", required = false,defaultValue = "https://www.baidu.com") String  uri,
                        @Valid UserMessage userMessage, BindingResult bindingResult, Model model){
        int id = userService.selectId(userMessage.getUsername(), userMessage.getPassword());
        if(id == 0) {
            redirect_uri = uri;
            return "redirect:http://localhost:8888";
        }
        else {
            return "redirect:"+redirect_uri+"?code="+id;
        }
    }
}
