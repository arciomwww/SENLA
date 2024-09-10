package org.example.entity;

import java.util.UUID;

public class Car {
    private UUID id;
    private String licensePlate;
    private UUID modelId;
    private UUID locationId;
    private String status;
    private double pricePerHour;
    private double pricePerDay;
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

    public static class Builder {
        private UUID id;
        private String licensePlate;
        private UUID modelId;
        private UUID locationId;
        private String status;
        private double pricePerHour;
        private double pricePerDay;
        private UUID insuranceId;

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder setModelId(UUID modelId) {
            this.modelId = modelId;
            return this;
        }

        public Builder setLocationId(UUID locationId) {
            this.locationId = locationId;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setPricePerHour(double pricePerHour) {
            this.pricePerHour = pricePerHour;
            return this;
        }

        public Builder setPricePerDay(double pricePerDay) {
            this.pricePerDay = pricePerDay;
            return this;
        }

        public Builder setInsuranceId(UUID insuranceId) {
            this.insuranceId = insuranceId;
            return this;
        }

        public Car build() {
            Car car = new Car();
            car.id = this.id;
            car.licensePlate = this.licensePlate;
            car.modelId = this.modelId;
            car.locationId = this.locationId;
            car.status = this.status;
            car.pricePerHour = this.pricePerHour;
            car.pricePerDay = this.pricePerDay;
            car.insuranceId = this.insuranceId;
            return car;
        }
    }
}

