package com.example.bugmanager.model;

public class Bug extends Identifiable<Integer>  {

    public String description;
    public String status;
    public Integer programmerId;

    public Bug(String description, String status, Integer programmerId) {
        this.description = description;
        this.status = status;
        this.programmerId = programmerId;
    }

    public Bug(Integer integer, String description, String status, Integer programmerId) {
        super(integer);
        this.description = description;
        this.status = status;
        this.programmerId = programmerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProgrammerId() {
        return programmerId;
    }

    public void setProgrammerId(Integer programmerId) {
        this.programmerId = programmerId;
    }
}
