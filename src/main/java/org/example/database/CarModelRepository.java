package org.example.database;

import org.example.entity.CarModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CarModelRepository {
    private final List<CarModel> carModels = new ArrayList<>();

    public CarModelRepository() {
        //mock
        carModels.add(new CarModel.Builder()
                .setId(UUID.fromString("6b997424-a6bf-486f-bb80-ea33fd225be0"))
                .setMake("Tesla")
                .setModel("Model S")
                .setYear(2020)
                .build());
        carModels.add(new CarModel.Builder()
                .setId(UUID.fromString("7c997424-a6bf-486f-bb80-ea33fd225be1"))
                .setMake("Ford")
                .setModel("Mustang")
                .setYear(2019)
                .build());
    }

    public List<CarModel> findAll() {
        return carModels;
    }

    public CarModel findById(UUID id) {
        return carModels.stream().filter(carModel -> carModel.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(CarModel carModel) {
        carModels.add(carModel);
    }

    public void delete(UUID id) {
        carModels.removeIf(carModel -> carModel.getId().equals(id));
    }
}