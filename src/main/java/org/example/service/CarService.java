package org.example.service;

import org.example.dto.CarDTO;
import org.example.database.CarRepositoryImpl;
import org.example.entity.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.annotation.Transaction;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarService {
    private static final Logger logger = LoggerFactory.getLogger(CarService.class);
    private CarRepositoryImpl carRepository;

    @Autowired
    public void setCarRepository(CarRepositoryImpl carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDTO> getAllCars() {
        logger.info("Fetching all cars");
        return carRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public CarDTO getCarById(UUID id) {
        logger.info("Fetching car by ID: {}", id);
        Car car = carRepository.findById(id);
        return car != null ? convertToDTO(car) : null;
    }

    @Transaction
    public void createCar(CarDTO carDTO) {
        logger.info("Creating car: {}", carDTO);
        Car car = convertToEntity(carDTO);
        carRepository.save(car);
    }

    @Transaction
    public void updateCar(UUID id, CarDTO carDTO) {
        logger.info("Updating car with ID: {}", id);
        Car car = carRepository.findById(id);
        if (car != null) {
            car.setLicensePlate(carDTO.getLicensePlate());
            car.setModelId(carDTO.getModelId());
            car.setLocationId(carDTO.getLocationId());
            car.setStatus(carDTO.getStatus());
            car.setPricePerHour(carDTO.getPricePerHour());
            car.setPricePerDay(carDTO.getPricePerDay());
            car.setInsuranceId(carDTO.getInsuranceId());
            carRepository.save(car);
            logger.info("Car updated successfully: {}", car);
        } else {
            logger.error("Car with ID: {} not found", id);
        }
    }

    @Transaction
    public void deleteCar(UUID id) {
        logger.info("Deleting car with ID: {}", id);
        carRepository.delete(id);
    }

    private CarDTO convertToDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setModelId(car.getModelId());
        carDTO.setLicensePlate(car.getLicensePlate());
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
