package org.example.database;

import org.example.entity.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository {
    private final List<Car> cars = new ArrayList<>();

    public CarRepository() {
        //mock
        cars.add(new Car.Builder()
                .setId(UUID.randomUUID())
                .setLicensePlate("ABC123")
                .setModelId(UUID.randomUUID())
                .setLocationId(UUID.randomUUID())
                .setStatus("Available")
                .setPricePerHour(10.0)
                .setPricePerDay(50.0)
                .setInsuranceId(UUID.randomUUID())
                .build());
        cars.add(new Car.Builder()
                .setId(UUID.randomUUID())
                .setLicensePlate("XYZ789")
                .setModelId(UUID.randomUUID())
                .setLocationId(UUID.randomUUID())
                .setStatus("Unavailable")
                .setPricePerHour(15.0)
                .setPricePerDay(70.0)
                .setInsuranceId(UUID.randomUUID())
                .build());
    }

    public List<Car> findAll() {
        return cars;
    }

    public Car findById(UUID id) {
        return cars.stream().filter(car -> car.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(Car car) {
        cars.add(car);
    }

    public void delete(UUID id) {
        cars.removeIf(car -> car.getId().equals(id));
    }
}
