package com.example.bugmanager.repository;


public interface Repository<ID, T> {
    void add(T elem);
    void update(ID id, T elem);

    void delete(ID id);

    T findOne(ID id);
    Iterable<T> findAll();
}
