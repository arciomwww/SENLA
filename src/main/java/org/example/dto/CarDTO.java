package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class CarDTO {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("license_plate")
    private String licensePlate;

    @JsonProperty("model_id")
    private UUID modelId;

    @JsonProperty("location_id")
    private UUID locationId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("price_per_hour")
    private double pricePerHour;

    @JsonProperty("price_per_day")
    private double pricePerDay;

    @JsonProperty("insurance_id")
    private UUID insuranceId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public UUID getModelId() {
        return modelId;
    }

    public void setModelId(UUID modelId) {
        this.modelId = modelId;
    }

    public UUID getLocationId() {
        return locationId;
    }

    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public UUID getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(UUID insuranceId) {
        this.insuranceId = insuranceId;
    }

}