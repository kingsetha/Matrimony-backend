package com.ani.matrimony.model;

public class AdminResponse {
    private String message;
    private int userid;
    private String role;

    public AdminResponse(int userid, String message, String role) {
        this.message = message;
        this.userid = userid;
        this.role = role;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
