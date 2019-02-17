package com.wq.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableAuthorizationServer
@Controller
public class DemoApplication {

    @GetMapping("/order")
    public ResponseEntity<String> order() {
        ResponseEntity<String> responseEntity = new ResponseEntity("order", HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/free")
    public ResponseEntity<String> test() {
        ResponseEntity<String> responseEntity = new ResponseEntity("free", HttpStatus.OK);
        return responseEntity;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

