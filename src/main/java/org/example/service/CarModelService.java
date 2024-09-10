package org.example.service;

import org.example.dto.CarModelDTO;
import org.example.database.CarModelRepository;
import org.example.entity.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarModelService {
    private CarModelRepository carModelRepository;

    @Autowired
    public void setCarModelRepository(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    public List<CarModelDTO> getAllCarModels() {
        return carModelRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public CarModelDTO getCarModelById(UUID id) {
        CarModel carModel = carModelRepository.findById(id);
        return carModel != null ? convertToDTO(carModel) : null;
    }

    public void createCarModel(CarModelDTO carModelDTO) {
        CarModel carModel = convertToEntity(carModelDTO);
        carModelRepository.save(carModel);
    }

    public void updateCarModel(UUID id, CarModelDTO carModelDTO) {
        CarModel carModel = carModelRepository.findById(id);
        if (carModel != null) {
            carModel.setMake(carModelDTO.getMake());
            carModel.setModel(carModelDTO.getModel());
            carModel.setYear(carModelDTO.getYear());
            carModelRepository.save(carModel);
        }
    }

    public void deleteCarModel(UUID id) {
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