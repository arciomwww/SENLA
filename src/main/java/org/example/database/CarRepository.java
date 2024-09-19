package org.example.database;

import org.example.entity.Car;
import java.util.List;
import java.util.UUID;

public interface CarRepository {
    List<Car> findAll();
    Car findById(UUID id);
    void save(Car car);
    void delete(UUID id);
}
