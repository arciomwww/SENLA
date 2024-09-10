package org.example.service;

import org.example.dto.CarDTO;
import org.example.database.CarRepository;
import org.example.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarService {
    private CarRepository carRepository;

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public CarDTO getCarById(UUID id) {
        Car car = carRepository.findById(id);
        return car != null ? convertToDTO(car)
                : null;
    }

    public void createCar(CarDTO carDTO) {
        Car car = convertToEntity(carDTO);
        carRepository.save(car)
        ;
    }

    public void updateCar(UUID id, CarDTO carDTO) {
        Car car = carRepository.findById(id);
        if (car != null) {
            car.setLicensePlate(carDTO.getLicensePlate());
            car.setModelId(carDTO.getModelId());
            car.setLocationId(carDTO.getLocationId());
            car.setStatus(carDTO.getStatus());
            car.setPricePerHour(carDTO.getPricePerHour());
            car.setPricePerDay(carDTO.getPricePerDay());
            car.setInsuranceId(carDTO.getInsuranceId());
            carRepository.save(car)
            ;
        }
    }

    public void deleteCar(UUID id) {
        carRepository.delete(id);
    }

    private CarDTO convertToDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setLicensePlate(car.getLicensePlate());
        carDTO.setModelId(car.getModelId());
        carDTO.setLocationId(car.getLocationId());
        carDTO.setStatus(car.getStatus());
        carDTO.setPricePerHour(car.getPricePerHour());
        carDTO.setPricePerDay(car.getPricePerDay());
        carDTO.setInsuranceId(car.getInsuranceId());
        return carDTO;
    }

    private Car convertToEntity(CarDTO carDTO) {
        return new Car.Builder()
                .setId(carDTO.getId())
                .setLicensePlate(carDTO.getLicensePlate())
                .setModelId(carDTO.getModelId())
                .setLocationId(carDTO.getLocationId())
                .setStatus(carDTO.getStatus())
                .setPricePerHour(carDTO.getPricePerHour())
                .setPricePerDay(carDTO.getPricePerDay())
                .setInsuranceId(carDTO.getInsuranceId())
                .build();
    }
}
