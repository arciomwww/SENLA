package org.example.entity;

import java.util.UUID;

public class Location {
    private UUID id;
    private String name;
    private String address;
    private String city;
    private String country;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static class Builder {
        private UUID id;
        private String name;
        private String address;
        private String city;
        private String country;

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Location build() {
            Location location = new Location();
            location.id = this.id;
            location.name = this.name;
            location.address = this.address;
            location.city = this.city;
            location.country = this.country;
            return location;
        }
    }
}
