package org.example.database;

import org.example.entity.Insurance;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class InsuranceRepository {
    private final List<Insurance> insurances = new ArrayList<>();

    public InsuranceRepository() {
        //mock
        insurances.add(new Insurance.Builder()
                .setId(UUID.randomUUID())
                .setPolicyNumber("POL123")
                .setProvider("Provider A")
                .setCoverageAmount(100000.0)
                .build());
        insurances.add(new Insurance.Builder()
                .setId(UUID.randomUUID())
                .setPolicyNumber("POL456")
                .setProvider("Provider B")
                .setCoverageAmount(200000.0)
                .build());
    }

    public List<Insurance> findAll() {
        return insurances;
    }

    public Insurance findById(UUID id) {
        return insurances.stream().filter(insurance -> insurance.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(Insurance insurance) {
        insurances.add(insurance);
    }

    public void delete(UUID id) {
        insurances.removeIf(insurance -> insurance.getId().equals(id));
    }
}
