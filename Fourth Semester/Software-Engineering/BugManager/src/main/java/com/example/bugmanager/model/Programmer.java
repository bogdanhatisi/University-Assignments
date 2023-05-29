package com.example.bugmanager.model;
import javax.persistence.*;
// Other necessary imports



public class Programmer extends Identifiable<Integer>{


    private String username;
    private String password;

    private Integer projectId;


    public Programmer() {
    }

    public Programmer(String username, String password, Integer projectId) {
        this.username = username;
        this.password = password;
        this.projectId = projectId;
    }

    public Programmer(Integer integer, String username, String password, Integer projectId) {
        super(integer);
        this.username = username;
        this.password = password;
        this.projectId = projectId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
