package com.wq.controller;

import com.wq.entity.master_client;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ClientController {

    List<master_client> clients = new ArrayList<>();

    @GetMapping("/try")
    public @ResponseBody UUID getTry(){
        UUID guid = UUID.randomUUID();
        return guid;
    }

    @GetMapping("/clients")
    public @ResponseBody List<master_client> getAllClients(){
        return clients;
    }

    @PostMapping("/clients")
    public List<master_client> insertClient(@RequestBody master_client m){
        clients.add(m);
        return clients;
    }

    @PutMapping("/clients/{id}")
    public @ResponseBody List<master_client> update(@PathVariable("id") int id,@RequestBody master_client m){
        clients.set(id-1,m);
        return clients;
    }
}
