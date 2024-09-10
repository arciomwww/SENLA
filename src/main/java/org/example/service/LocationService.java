package org.example.service;

import org.example.dto.LocationDTO;
import org.example.database.LocationRepository;
import org.example.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private LocationRepository locationRepository;

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public LocationDTO getLocationById(UUID id) {
        Location location = locationRepository.findById(id);
        return location != null ? convertToDTO(location) : null;
    }

    public void createLocation(LocationDTO locationDTO) {
        Location location = convertToEntity(locationDTO);
        locationRepository.save(location);
    }

    public void updateLocation(UUID id, LocationDTO locationDTO) {
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

