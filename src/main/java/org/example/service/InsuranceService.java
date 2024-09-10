package org.example.service;

import org.example.dto.InsuranceDTO;
import org.example.database.InsuranceRepository;
import org.example.entity.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InsuranceService {
    private InsuranceRepository insuranceRepository;

    @Autowired
    public void setInsuranceRepository(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    public List<InsuranceDTO> getAllInsurances() {
        return insuranceRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public InsuranceDTO getInsuranceById(UUID id) {
        Insurance insurance = insuranceRepository.findById(id);
        return insurance != null ? convertToDTO(insurance) : null;
    }

    public void createInsurance(InsuranceDTO insuranceDTO) {
        Insurance insurance = convertToEntity(insuranceDTO);
        insuranceRepository.save(insurance);
    }

    public void updateInsurance(UUID id, InsuranceDTO insuranceDTO) {
        Insurance insurance = insuranceRepository.findById(id);
        if (insurance != null) {
            insurance.setPolicyNumber(insuranceDTO.getPolicyNumber());
            insurance.setProvider(insuranceDTO.getProvider());
            insurance.setCoverageAmount(insuranceDTO.getCoverageAmount());
            insuranceRepository.save(insurance);
        }
    }

    public void deleteInsurance(UUID id) {
        insuranceRepository.delete(id);
    }

    private InsuranceDTO convertToDTO(Insurance insurance) {
        InsuranceDTO insuranceDTO = new InsuranceDTO();
        insuranceDTO.setId(insurance.getId());
        insuranceDTO.setPolicyNumber(insurance.getPolicyNumber());
        insuranceDTO.setProvider(insurance.getProvider());
        insuranceDTO.setCoverageAmount(insurance.getCoverageAmount());
        return insuranceDTO;
    }

    private Insurance convertToEntity(InsuranceDTO insuranceDTO) {
        return new Insurance.Builder()
                .setId(insuranceDTO.getId())
                .setPolicyNumber(insuranceDTO.getPolicyNumber())
                .setProvider(insuranceDTO.getProvider())
                .setCoverageAmount(insuranceDTO.getCoverageAmount())
                .build();
    }
}

