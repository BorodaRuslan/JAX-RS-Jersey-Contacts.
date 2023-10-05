package org.example.app.repository;

import java.util.List;

public interface AppRepository<T>{
    List<T> fetchAll();
    void create(T obj);
    T fetchById(int id);
    void update(int id, T obj);
    void delete(int id);
}
