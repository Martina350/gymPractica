package com.puce.gym.service;

import java.util.List;
import java.util.Optional;

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
