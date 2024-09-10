package org.example.entity;

import java.util.UUID;
import java.util.Date;

public class Rental {
    private UUID id;
    private UUID carId;
    private UUID userId;
    private Date startDate;
    private Date endDate;
    private double totalPrice;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static class Builder {
        private UUID id;
        private UUID carId;
        private UUID userId;
        private Date startDate;
        private Date endDate;
        private double totalPrice;

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setCarId(UUID carId) {
            this.carId = carId;
            return this;
        }

        public Builder setUserId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder setStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Rental build() {
            Rental rental = new Rental();
            rental.id = this.id;
            rental.carId = this.carId;
            rental.userId = this.userId;
            rental.startDate = this.startDate;
            rental.endDate = this.endDate;
            rental.totalPrice = this.totalPrice;
            return rental;
        }
    }
}
