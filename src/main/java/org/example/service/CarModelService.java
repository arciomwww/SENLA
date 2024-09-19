package org.example.service;

import org.example.dto.CarModelDTO;
import org.example.database.CarModelRepository;
import org.example.entity.CarModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarModelService {
    private static final Logger logger = LoggerFactory.getLogger(CarModelService.class);
    private CarModelRepository carModelRepository;

    @Autowired
    public void setCarModelRepository(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    public List<CarModelDTO> getAllCarModels() {
        logger.info("Fetching all car models");
        return carModelRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public CarModelDTO getCarModelById(UUID id) {
        logger.info("Fetching car model by ID: {}", id);
        CarModel carModel = carModelRepository.findById(id);
        return carModel != null ? convertToDTO(carModel) : null;
    }

    public void createCarModel(CarModelDTO carModelDTO) {
        logger.info("Creating car model: {}", carModelDTO);
        CarModel carModel = convertToEntity(carModelDTO);
        carModelRepository.save(carModel);
    }

    public void updateCarModel(UUID id, CarModelDTO carModelDTO) {
        logger.info("Updating car model with ID: {}", id);
        CarModel carModel = carModelRepository.findById(id);
        if (carModel != null) {
            carModel.setId(carModelDTO.getId());
            carModel.setMake(carModelDTO.getMake());
            carModel.setModel(carModelDTO.getModel());
            carModel.setYear(carModelDTO.getYear());
            carModelRepository.save(carModel);
        }
    }

    public void deleteCarModel(UUID id) {
        logger.info("Deleting car model with ID: {}", id);
        carModelRepository.delete(id);
    }

    private CarModelDTO convertToDTO(CarModel carModel) {
        CarModelDTO carModelDTO = new CarModelDTO();
        carModelDTO.setId(carModel.getId());
        carModelDTO.setMake(carModel.getMake());
        carModelDTO.setModel(carModel.getModel());
        carModelDTO.setYear(carModel.getYear());
        return carModelDTO;
    }

    private CarModel convertToEntity(CarModelDTO carModelDTO) {
        return new CarModel.Builder()
                .setId(carModelDTO.getId())
                .setMake(carModelDTO.getMake())
                .setModel(carModelDTO.getModel())
                .setYear(carModelDTO.getYear())
                .build();
    }
}
