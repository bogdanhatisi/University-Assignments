package com.example.demo.domain;

public interface Identifiable<ID> {
    void setId(ID id);
    ID getId();
}
