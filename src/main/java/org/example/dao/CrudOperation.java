package org.example.dao;

import org.example.entity.DishAvailability;

import java.util.List;

public interface CrudOperation<E> {
    List<DishAvailability> findAll();
}
