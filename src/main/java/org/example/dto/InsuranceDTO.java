package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class InsuranceDTO {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("policy_number")
    private String policyNumber;

    @JsonProperty("provider")
    private String provider;

    @JsonProperty("coverage_amount")
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
}
