package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.annotation.Transaction;
import org.example.dto.LocationDTO;
import org.example.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/locations")
public class LocationController {
    private LocationService locationService;
    private ObjectMapper objectMapper;

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/initialize")
    @Transaction
    public void initializeLocations() {
        LocationDTO location1 = new LocationDTO();
        location1.setId(UUID.fromString("8c997424-a6bf-486f-bb80-ea33fd225be2"));
        location1.setName("Location 1");
        location1.setAddress("123 Main St");
        location1.setCity("City A");
        location1.setCountry("State A");

        LocationDTO location2 = new LocationDTO();
        location2.setId(UUID.fromString("9c997424-a6bf-486f-bb80-ea33fd225be3"));
        location2.setName("Location 2");
        location2.setAddress("456 Elm St");
        location2.setCity("City B");
        location2.setCountry("State B");

        locationService.createLocation(location1);
        locationService.createLocation(location2);

    }

    @GetMapping
    public String getAllLocations() throws Exception {
        List<LocationDTO> locations = locationService.getAllLocations();
        return objectMapper.writeValueAsString(locations);
    }

    @GetMapping("/{id}")
    public String getLocationById(@PathVariable UUID id) throws Exception {
        LocationDTO location = locationService.getLocationById(id);
        return objectMapper.writeValueAsString(location);
    }

    @PostMapping
    public void createLocation(@RequestBody LocationDTO locationDTO) {
        locationService.createLocation(locationDTO);
    }

    @PutMapping("/{id}")
    public void updateLocation(@PathVariable UUID id, @RequestBody LocationDTO locationDTO) {
        locationService.updateLocation(id, locationDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable UUID id) {
        locationService.deleteLocation(id);
    }

    @GetMapping("/test")
    public void testCRUDOperations() throws Exception {
// Инициализация
        initializeLocations();

        List<LocationDTO> locations = locationService.getAllLocations();
        LocationDTO locationToRead = locations.get(0);
        System.out.println("Read Location: " + objectMapper.writeValueAsString(locationService.getLocationById(locationToRead.getId())));

        LocationDTO locationToDelete = locations.get(1);
        locationService.deleteLocation(locationToDelete.getId());

        locationToRead.setName("UPDATED NAME");
        locationService.updateLocation(locationToRead.getId(), locationToRead);

        System.out.println("Updated Location: " + objectMapper.writeValueAsString(locationService.getLocationById(locationToRead.getId())));
    }
}


