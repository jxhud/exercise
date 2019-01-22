package com.wq.entity;

public class master_user {
    private int id;
    private String username;
    private String password;
    private String display_name;

    public master_user() {
    }

    public master_user(int id, String username, String password, String display_name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.display_name = display_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
