package org.example.database;

import org.example.entity.Rental;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class RentalRepository {
    private final List<Rental> rentals = new ArrayList<>();

    public RentalRepository() {
        //mock
        rentals.add(new Rental.Builder()
                .setId(UUID.randomUUID())
                .setCarId(UUID.randomUUID())
                .setUserId(UUID.randomUUID())
                .setStartDate(new Date())
                .setEndDate(new Date())
                .setTotalPrice(100.0)
                .build());
        rentals.add(new Rental.Builder()
                .setId(UUID.randomUUID())
                .setCarId(UUID.randomUUID())
                .setUserId(UUID.randomUUID())
                .setStartDate(new Date())
                .setEndDate(new Date())
                .setTotalPrice(200.0)
                .build());
    }

    public List<Rental> findAll() {
        return rentals;
    }

    public Rental findById(UUID id) {
        return rentals.stream().filter(rental -> rental.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(Rental rental) {
        rentals.add(rental);
    }

    public void delete(UUID id) {
        rentals.removeIf(rental -> rental.getId().equals(id));
    }
}
