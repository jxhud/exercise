package com.wq.demo.entity;

public class UserInfo {
    private int id;
    private String display_name;

    public UserInfo() {
    }

    public UserInfo(int id, String display_name) {
        this.id = id;
        this.display_name = display_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
