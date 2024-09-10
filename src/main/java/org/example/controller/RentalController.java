package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.RentalDTO;
import org.example.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Date;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    private RentalService rentalService;
    private ObjectMapper objectMapper;

    @Autowired
    public void setRentalService(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/initialize")
    public void initializeRentals() {
        RentalDTO rental1 = new RentalDTO();
        rental1.setId(UUID.randomUUID());
        rental1.setCarId(UUID.randomUUID());
        rental1.setUserId(UUID.randomUUID());
        rental1.setStartDate(new Date());
        rental1.setEndDate(new Date());
        rental1.setTotalPrice(100.0);

        RentalDTO rental2 = new RentalDTO();
        rental2.setId(UUID.randomUUID());
        rental2.setCarId(UUID.randomUUID());
        rental2.setUserId(UUID.randomUUID());
        rental2.setStartDate(new Date());
        rental2.setEndDate(new Date());
        rental2.setTotalPrice(200.0);

        rentalService.createRental(rental1);
        rentalService.createRental(rental2);
    }

    @GetMapping
    public String getAllRentals() throws Exception {
        List<RentalDTO> rentals = rentalService.getAllRentals();
        return objectMapper.writeValueAsString(rentals);
    }

    @GetMapping("/{id}")
    public String getRentalById(@PathVariable UUID id) throws Exception {
        RentalDTO rental = rentalService.getRentalById(id);
        return objectMapper.writeValueAsString(rental);
    }

    @PostMapping
    public void createRental(@RequestBody RentalDTO rentalDTO) {
        rentalService.createRental(rentalDTO);
    }

    @PutMapping("/{id}")
    public void updateRental(@PathVariable UUID id, @RequestBody RentalDTO rentalDTO) {
        rentalService.updateRental(id, rentalDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable UUID id) {
        rentalService.deleteRental(id);
    }

    @GetMapping("/test")
    public void testCRUDOperations() throws Exception {
        initializeRentals();

        List<RentalDTO> rentals = rentalService.getAllRentals();
        RentalDTO rentalToRead = rentals.get(0);
        System.out.println("Read Rental: " + objectMapper.writeValueAsString(rentalService.getRentalById(rentalToRead.getId())));

        RentalDTO rentalToDelete = rentals.get(1);
        rentalService.deleteRental(rentalToDelete.getId());

        rentalToRead.setTotalPrice(150.0);
        rentalService.updateRental(rentalToRead.getId(), rentalToRead);

        System.out.println("Updated Rental: " + objectMapper.writeValueAsString(rentalService.getRentalById(rentalToRead.getId())));
    }
}
