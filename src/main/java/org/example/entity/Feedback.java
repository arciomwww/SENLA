package org.example.entity;

import java.util.UUID;
import java.util.Date;

public class Feedback {
    private UUID id;
    private UUID userId;
    private UUID carId;
    private int rating;
    private String comment;
    private Date createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public static class Builder {
        private UUID id;
        private UUID userId;
        private UUID carId;
        private int rating;
        private String comment;
        private Date createdAt;

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder setCarId(UUID carId) {
            this.carId = carId;
            return this;
        }

        public Builder setRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Feedback build() {
            Feedback feedback = new Feedback();
            feedback.id = this.id;
            feedback.userId = this.userId;
            feedback.carId = this.carId;
            feedback.rating = this.rating;
            feedback.comment = this.comment;
            feedback.createdAt = this.createdAt;
            return feedback;
        }
    }
}