package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.CarDTO;
import org.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cars")
public class CarController {
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
    public void initializeCars() {
        CarDTO car1 = new CarDTO();
        car1.setId(UUID.randomUUID());
        car1.setLicensePlate("ABC123");
        car1.setModelId(UUID.randomUUID());
        car1.setLocationId(UUID.randomUUID());
        car1.setStatus("Available");
        car1.setPricePerHour(10.0);
        car1.setPricePerDay(50.0);
        car1.setInsuranceId(UUID.randomUUID());

        CarDTO car2 = new CarDTO();
        car2.setId(UUID.randomUUID());
        car2.setLicensePlate("XYZ789");
        car2.setModelId(UUID.randomUUID());
        car2.setLocationId(UUID.randomUUID());
        car2.setStatus("Unavailable");
        car2.setPricePerHour(15.0);
        car2.setPricePerDay(70.0);
        car2.setInsuranceId(UUID.randomUUID());

        carService.createCar(car1);
        carService.createCar(car2);

    }

    @GetMapping
    public String getAllCars() throws Exception {
        List<CarDTO> cars = carService.getAllCars();
        return objectMapper.writeValueAsString(cars);
    }

    @GetMapping("/{id}")
    public String getCarById(@PathVariable UUID id) throws Exception {
        CarDTO car = carService.getCarById(id);
        return objectMapper.writeValueAsString(car);
    }

    @PostMapping
    public void createCar(@RequestBody CarDTO carDTO) {
        carService.createCar(carDTO);
    }

    @PutMapping("/{id}")
    public void updateCar(@PathVariable UUID id, @RequestBody CarDTO carDTO) {
        carService.updateCar(id, carDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable UUID id) {
        carService.deleteCar(id);
    }

    @GetMapping("/test")
    public void testCRUDOperations() throws Exception {
        initializeCars();
        // Чтение одной записи
        List<CarDTO> cars = carService.getAllCars();
        CarDTO carToRead = cars.get(0);
        System.out.println("Read Car: " + objectMapper.writeValueAsString(carService.getCarById(carToRead.getId())));
        // Удаление второй записи
        CarDTO carToDelete = cars.get(1);
        carService.deleteCar(carToDelete.getId());
        // Обновление оставшейся записи
        carToRead.setStatus("Updated Status");
        carService.updateCar(carToRead.getId(), carToRead);
        // Повторное чтение обновленной записи
        System.out.println("Updated Car: " + objectMapper.writeValueAsString(carService.getCarById(carToRead.getId())));
    }
}