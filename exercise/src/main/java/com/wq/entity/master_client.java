package com.wq.entity;

public class master_client {
    private int id;
    private String client_name;
    private String client_secret;
    private String client_open_id;

    public master_client() {
    }

    public master_client(int id, String client_name, String client_secret, String client_open_id) {
        this.id = id;
        this.client_name = client_name;
        this.client_secret = client_secret;
        this.client_open_id = client_open_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getClient_open_id() {
        return client_open_id;
    }

    public void setClient_open_id(String client_open_id) {
        this.client_open_id = client_open_id;
    }
}
