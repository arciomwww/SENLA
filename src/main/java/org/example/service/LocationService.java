package org.example.service;

import org.example.dto.LocationDTO;
import org.example.database.LocationRepository;
import org.example.entity.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private static final Logger logger = LoggerFactory.getLogger(LocationService.class);
    private LocationRepository locationRepository;

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationDTO> getAllLocations() {
        logger.info("Fetching all locations");
        return locationRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public LocationDTO getLocationById(UUID id) {
        logger.info("Fetching location by ID: {}", id);
        Location location = locationRepository.findById(id);
        return location != null ? convertToDTO(location) : null;
    }

    public void createLocation(LocationDTO locationDTO) {
        logger.info("Creating location: {}", locationDTO);
        Location location = convertToEntity(locationDTO);
        locationRepository.save(location);
    }

    public void updateLocation(UUID id, LocationDTO locationDTO) {
        logger.info("Updating location with ID: {}", id);
        Location location = locationRepository.findById(id);
        if (location != null) {
            location.setName(locationDTO.getName());
            location.setAddress(locationDTO.getAddress());
            location.setCity(locationDTO.getCity());
            location.setCountry(locationDTO.getCountry());
            locationRepository.save(location);
        }
    }

    public void deleteLocation(UUID id) {
        logger.info("Deleting location with ID: {}", id);
        locationRepository.delete(id);
    }

    private LocationDTO convertToDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        locationDTO.setAddress(location.getAddress());
        locationDTO.setCity(location.getCity());
        locationDTO.setCountry(location.getCountry());
        return locationDTO;
    }

    private Location convertToEntity(LocationDTO locationDTO) {
        return new Location.Builder()
                .setId(locationDTO.getId())
                .setName(locationDTO.getName())
                .setAddress(locationDTO.getAddress())
                .setCity(locationDTO.getCity())
                .setCountry(locationDTO.getCountry())
                .build();
    }
}

