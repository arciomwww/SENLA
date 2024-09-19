package org.example.controller;
import org.example.annotation.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.CarDTO;
import org.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cars")
public class CarController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    private CarService carService;
    private ObjectMapper objectMapper;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/initialize")
    @Transaction
    public void initializeCars() {
        logger.info("Initializing cars");

        List<CarDTO> existingCars = carService.getAllCars();
        if (!existingCars.isEmpty()) {
            logger.info("Cars already initialized");
            return;
        }

        CarDTO car1 = new CarDTO();
        car1.setId(UUID.randomUUID());
        car1.setLicensePlate("ABC123");
        car1.setModelId(UUID.fromString("6b997424-a6bf-486f-bb80-ea33fd225be0"));
        car1.setLocationId(UUID.fromString("8c997424-a6bf-486f-bb80-ea33fd225be2"));
        car1.setStatus("Available");
        car1.setPricePerHour(10.0);
        car1.setPricePerDay(50.0);
        car1.setInsuranceId(UUID.fromString("ac997424-a6bf-486f-bb80-ea33fd225be4"));

        CarDTO car2 = new CarDTO();
        car2.setId(UUID.randomUUID());
        car2.setLicensePlate("XYZ789");
        car2.setModelId(UUID.fromString("7c997424-a6bf-486f-bb80-ea33fd225be1"));
        car2.setLocationId(UUID.fromString("9c997424-a6bf-486f-bb80-ea33fd225be3"));
        car2.setStatus("Unavailable");
        car2.setPricePerHour(15.0);
        car2.setPricePerDay(70.0);
        car2.setInsuranceId(UUID.fromString("bc997424-a6bf-486f-bb80-ea33fd225be5"));

        carService.createCar(car1);
        carService.createCar(car2);
        logger.info("Cars initialized successfully");
    }


    @GetMapping
    public String getAllCars() throws Exception {
        logger.info("Fetching all cars");
        List<CarDTO> cars = carService.getAllCars();
        String result = objectMapper.writeValueAsString(cars);
        logger.info("Fetched all cars: {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public String getCarById(@PathVariable UUID id) throws Exception {
        logger.info("Fetching car with ID: {}", id);
        CarDTO car = carService.getCarById(id);
        String result = objectMapper.writeValueAsString(car);
        logger.info("Fetched car: {}", result);
        return result;
    }

    @PostMapping
    @Transaction
    public void createCar(@RequestBody CarDTO carDTO) {
        logger.info("Creating car: {}", carDTO);
        carService.createCar(carDTO);
        logger.info("Car created successfully");
    }

    @PutMapping("/{id}")
    @Transaction
    public void updateCar(@PathVariable UUID id, @RequestBody CarDTO carDTO) {
        logger.info("Updating car with ID: {}", id);
        carService.updateCar(id, carDTO);
        logger.info("Car updated successfully");
    }

    @DeleteMapping("/{id}")
    @Transaction
    public void deleteCar(@PathVariable UUID id) {
        logger.info("Deleting car with ID: {}", id);
        carService.deleteCar(id);
        logger.info("Car deleted successfully");
    }
    @GetMapping("/test")
    public void testCRUDOperations() throws Exception {
        initializeCars();

        List<CarDTO> cars = carService.getAllCars();
        CarDTO carToRead = cars.get(0);
        System.out.println("Read Car: " + objectMapper.writeValueAsString(carService.getCarById(carToRead.getId())));

        CarDTO carToDelete = cars.get(1);
        carService.deleteCar(carToDelete.getId());

        carToRead.setStatus("Updated");
        carService.updateCar(carToRead.getId(), carToRead);

        System.out.println("Updated Car: " + objectMapper.writeValueAsString(carService.getCarById(carToRead.getId())));
    }
}