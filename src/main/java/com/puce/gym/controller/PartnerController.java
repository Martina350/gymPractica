package com.puce.gym.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puce.gym.models.Partner;
import com.puce.gym.service.PartnerService;

@RestController 
@RequestMapping ("/api/partners")
public class PartnerController {
    private PartnerService partnerService;
    
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping 
    public List<Partner> getAllPartners(){
        return partnerService.getAllPartners();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partner> getPartnerById(@PathVariable int id) {
        return partnerService.getPartnerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Partner>> getPartnersActive(){
        return ResponseEntity.ok(partnerService.getPartnersActive(true));
    }

    @GetMapping("/plan/{plan}")
    public ResponseEntity<List<Partner>> getPartnersByPlan(@PathVariable String plan){
        return ResponseEntity.ok(partnerService.getPartnersByPlan(plan));
    }

    @GetMapping("/income")
    public ResponseEntity<Double> getTotalMonthlyPayments() {
        return partnerService.getTotalMonthlyPayments();
    }

    @GetMapping("income-by-plan")
    public ResponseEntity<Map<String,Double>> getIncomeByPlan() {
        return ResponseEntity.ok(partnerService.getIncomeByPlan());
    }


    @PostMapping("/create")
    public ResponseEntity<Partner> createPartner(@RequestBody Partner partner){
        Partner createdPartner = partnerService.createPartner(partner);
        return new ResponseEntity<>(createdPartner, HttpStatus.CREATED);        
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Partner> updatePartner(@PathVariable int id, @RequestBody Partner partnerUpdated) {
        return partnerService.updatePartner(id, partnerUpdated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePartner(@PathVariable int id) {
        if (partnerService.deletePartner(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
