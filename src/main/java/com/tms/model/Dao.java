package com.tms.model;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(String name);

    Optional<T> get(String name, String password);

    List<T> getAll();

    void save(T t);

    void delete(String name);

    void edit(T t);
}