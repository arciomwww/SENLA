package org.example.entity;

import java.util.UUID;

public class CarModel {
    private UUID id;
    private String make;
    private String model;
    private int year;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static class Builder {
        private UUID id;
        private String make;
        private String model;
        private int year;

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setMake(String make) {
            this.make = make;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public CarModel build() {
            CarModel carModel = new CarModel();
            carModel.id = this.id;
            carModel.make = this.make;
            carModel.model = this.model;
            carModel.year = this.year;
            return carModel;
        }
    }
}