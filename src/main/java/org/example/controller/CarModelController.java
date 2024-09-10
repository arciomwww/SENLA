package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.CarModelDTO;
import org.example.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/car-models")
public class CarModelController {
    private CarModelService carModelService;
    private ObjectMapper objectMapper;

    @Autowired
    public void setCarModelService(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/initialize")
    public void initializeCarModels() {
        CarModelDTO carModel1 = new CarModelDTO();
        carModel1.setId(UUID.randomUUID());
        carModel1.setMake("Tesla");
        carModel1.setModel("Model S");
        carModel1.setYear(2020);

        CarModelDTO carModel2 = new CarModelDTO();
        carModel2.setId(UUID.randomUUID());
        carModel2.setMake("Ford");
        carModel2.setModel("Mustang");
        carModel2.setYear(2019);

        carModelService.createCarModel(carModel1);
        carModelService.createCarModel(carModel2);
    }

    @GetMapping
    public String getAllCarModels() throws Exception {
        List<CarModelDTO> carModels = carModelService.getAllCarModels();
        return objectMapper.writeValueAsString(carModels);
    }

    @GetMapping("/{id}")
    public String getCarModelById(@PathVariable UUID id) throws Exception {
        CarModelDTO carModel = carModelService.getCarModelById(id);
        return objectMapper.writeValueAsString(carModel);
    }

    @PostMapping
    public void createCarModel(@RequestBody CarModelDTO carModelDTO) {
        carModelService.createCarModel(carModelDTO);
    }

    @PutMapping("/{id}")
    public void updateCarModel(@PathVariable UUID id, @RequestBody CarModelDTO carModelDTO) {
        carModelService.updateCarModel(id, carModelDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCarModel(@PathVariable UUID id) {
        carModelService.deleteCarModel(id);
    }

    @GetMapping("/test")
    public void testCRUDOperations() throws Exception {
        initializeCarModels();

        List<CarModelDTO> carModels = carModelService.getAllCarModels();
        CarModelDTO carModelToRead = carModels.get(0);
        System.out.println("Read CarModel: " + objectMapper.writeValueAsString(carModelService.getCarModelById(carModelToRead.getId())));

        CarModelDTO carModelToDelete = carModels.get(1);
        carModelService.deleteCarModel(carModelToDelete.getId());

        carModelToRead.setMake("UPDATED MAKE");
        carModelService.updateCarModel(carModelToRead.getId(), carModelToRead);

        System.out.println("Updated CarModel: " + objectMapper.writeValueAsString(carModelService.getCarModelById(carModelToRead.getId())));
    }
}