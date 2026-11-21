package com.puce.gym.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.puce.gym.models.Partner;
import com.puce.gym.repository.PartnerRepository;

@Service 
public class PartnerService {
    public PartnerRepository partnerRepository;
    
    private PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }
    
    public List<Partner> getAllPartners(){
        return partnerRepository.findAll();
    }

    public Optional<Partner> getPartnerById(int id){
        return partnerRepository.findById(id);
    }

    public List<Partner> getPartnersActive(boolean isActive){
        return partnerRepository.findByIsActive(isActive);
    }

    public List<Partner> getPartnersByPlan(String plan){
        return partnerRepository.findByPlanIgnoreCase(plan);
    }

    public ResponseEntity<Double> getTotalMonthlyPayments() {
        List<Partner> activePartners = partnerRepository.findByIsActive(true);
        double totalPayments = activePartners.stream()
                .mapToDouble(Partner::getMonthlyPayment)
                .sum();
        return ResponseEntity.ok(totalPayments);
    }

    public Map <String, Double> getIncomeByPlan (){
        List<Partner> activePartners = partnerRepository.findByIsActive(true);
        return activePartners.stream()
            .collect(Collectors.groupingBy(
                    Partner::getPlan,
                    Collectors.summingDouble(Partner::getMonthlyPayment)
            ));
    }
    
    public ResponseEntity<Partner> activatePartner(int id) {
        return updatePartnerStatus(id, true);
    }

    public ResponseEntity<Partner> deactivatePartner(int id) {
        return updatePartnerStatus(id, false);
    }

    private ResponseEntity<Partner> updatePartnerStatus(int id, boolean newStatus) {
        Optional<Partner> optionalPartner = partnerRepository.findById(id);
            if (optionalPartner.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        Partner partner = optionalPartner.get();
            if (partner.isActive() == newStatus) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            partner.setActive(newStatus);
        Partner savedPartner = partnerRepository.save(partner);
            return ResponseEntity.ok(savedPartner);
    }

    public Partner createPartner(Partner partner){
        return partnerRepository.save(partner);
    }

    public Optional<Partner> updatePartner(int id, Partner partnerUpdated){
        return partnerRepository.findById(id).map(partner -> {
            partner.setName(partnerUpdated.getName());
            partner.setPlan(partnerUpdated.getPlan());
            partner.setMonthlyPayment(partnerUpdated.getMonthlyPayment());
            return partnerRepository.save(partner);
        });
    }

    public boolean deletePartner(int id){
        if (partnerRepository.existsById(id)) {
            partnerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
