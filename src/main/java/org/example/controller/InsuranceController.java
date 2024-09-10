package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.InsuranceDTO;
import org.example.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/insurances")
public class InsuranceController {
    private InsuranceService insuranceService;
    private ObjectMapper objectMapper;

    @Autowired
    public void setInsuranceService(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/initialize")
    public void initializeInsurances() {
        InsuranceDTO insurance1 = new InsuranceDTO();
        insurance1.setId(UUID.randomUUID());
        insurance1.setPolicyNumber("POL12345");
        insurance1.setProvider("Provider A");
        insurance1.setCoverageAmount(100000.0);

        InsuranceDTO insurance2 = new InsuranceDTO();
        insurance2.setId(UUID.randomUUID());
        insurance2.setPolicyNumber("POL67890");
        insurance2.setProvider("Provider B");
        insurance2.setCoverageAmount(200000.0);

        insuranceService.createInsurance(insurance1);
        insuranceService.createInsurance(insurance2);
    }

    @GetMapping
    public String getAllInsurances() throws Exception {
        List<InsuranceDTO> insurances = insuranceService.getAllInsurances();
        return objectMapper.writeValueAsString(insurances);
    }

    @GetMapping("/{id}")
    public String getInsuranceById(@PathVariable UUID id) throws Exception {
        InsuranceDTO insurance = insuranceService.getInsuranceById(id);
        return objectMapper.writeValueAsString(insurance);
    }

    @PostMapping
    public void createInsurance(@RequestBody InsuranceDTO insuranceDTO) {
        insuranceService.createInsurance(insuranceDTO);
    }

    @PutMapping("/{id}")
    public void updateInsurance(@PathVariable UUID id, @RequestBody InsuranceDTO insuranceDTO) {
        insuranceService.updateInsurance(id, insuranceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteInsurance(@PathVariable UUID id) {
        insuranceService.deleteInsurance(id);
    }

    @GetMapping("/test")
    public void testCRUDOperations() throws Exception {
        initializeInsurances();

        List<InsuranceDTO> insurances = insuranceService.getAllInsurances();
        InsuranceDTO insuranceToRead = insurances.get(0);
        System.out.println("Read Insurance: " + objectMapper.writeValueAsString(insuranceService.getInsuranceById(insuranceToRead.getId())));

        InsuranceDTO insuranceToDelete = insurances.get(1);
        insuranceService.deleteInsurance(insuranceToDelete.getId());

        insuranceToRead.setProvider("UPDATED PROVIDER");
        insuranceService.updateInsurance(insuranceToRead.getId(), insuranceToRead);

        System.out.println("Updated Insurance: " + objectMapper.writeValueAsString(insuranceService.getInsuranceById(insuranceToRead.getId())));
    }
}
