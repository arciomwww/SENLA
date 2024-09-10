package org.example.database;

import org.example.entity.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class LocationRepository {
    private final List<Location> locations = new ArrayList<>();

    public LocationRepository() {
        //mock
        locations.add(new Location.Builder()
                .setId(UUID.randomUUID())
                .setName("Location 1")
                .setAddress("123 Main St")
                .setCity("City A")
                .setCountry("State A")
                .build());
        locations.add(new Location.Builder()
                .setId(UUID.randomUUID())
                .setName("Location 2")
                .setAddress("456 Elm St")
                .setCity("City B")
                .setCountry("State B")
                .build());
    }

    public List<Location> findAll() {
        return locations;
    }

    public Location findById(UUID id) {
        return locations.stream().filter(location -> location.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(Location location) {
        locations.add(location);
    }

    public void delete(UUID id) {
        locations.removeIf(location -> location.getId().equals(id));
    }
}
