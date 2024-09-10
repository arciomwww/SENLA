package org.example.service;

import org.example.dto.RentalDTO;
import org.example.database.RentalRepository;
import org.example.entity.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private RentalRepository rentalRepository;

    @Autowired
    public void setRentalRepository(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<RentalDTO> getAllRentals() {
        return rentalRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public RentalDTO getRentalById(UUID id) {
        Rental rental = rentalRepository.findById(id);
        return rental != null ? convertToDTO(rental) : null;
    }

    public void createRental(RentalDTO rentalDTO) {
        Rental rental = convertToEntity(rentalDTO);
        rentalRepository.save(rental);
    }

    public void updateRental(UUID id, RentalDTO rentalDTO) {
        Rental rental = rentalRepository.findById(id);
        if (rental != null) {
            rental.setStartDate(rentalDTO.getStartDate());
            rental.setEndDate(rentalDTO.getEndDate());
            rental.setTotalPrice(rentalDTO.getTotalPrice());
            rentalRepository.save(rental);
        }
    }

    public void deleteRental(UUID id) {
        rentalRepository.delete(id);
    }

    private RentalDTO convertToDTO(Rental rental) {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(rental.getId());
        rentalDTO.setCarId(rental.getCarId());
        rentalDTO.setUserId(rental.getUserId());
        rentalDTO.setStartDate(rental.getStartDate());
        rentalDTO.setEndDate(rental.getEndDate());
        rentalDTO.setTotalPrice(rental.getTotalPrice());
        return rentalDTO;
    }

    private Rental convertToEntity(RentalDTO rentalDTO) {
        return new Rental.Builder()
                .setId(rentalDTO.getId())
                .setCarId(rentalDTO.getCarId())
                .setUserId(rentalDTO.getUserId())
                .setStartDate(rentalDTO.getStartDate())
                .setEndDate(rentalDTO.getEndDate())
                .setTotalPrice(rentalDTO.getTotalPrice())
                .build();
    }
}