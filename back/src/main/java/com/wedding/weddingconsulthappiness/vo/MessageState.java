package com.wedding.weddingconsulthappiness.vo;

public class MessageState {
    private int id;
    private String username;
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MessageState(int id, String username, String state) {
        this.id = id;
        this.username = username;
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
