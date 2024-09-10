package org.example.entity;

import java.util.UUID;

public class Insurance {
    private UUID id;
    private String policyNumber;
    private String provider;
    private double coverageAmount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public static class Builder {
        private UUID id;
        private String policyNumber;
        private String provider;
        private double coverageAmount;

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setPolicyNumber(String policyNumber) {
            this.policyNumber = policyNumber;
            return this;
        }

        public Builder setProvider(String provider) {
            this.provider = provider;
            return this;
        }

        public Builder setCoverageAmount(double coverageAmount) {
            this.coverageAmount = coverageAmount;
            return this;
        }

        public Insurance build() {
            Insurance insurance = new Insurance();
            insurance.id = this.id;
            insurance.policyNumber = this.policyNumber;
            insurance.provider = this.provider;
            insurance.coverageAmount = this.coverageAmount;
            return insurance;
        }
    }
}
