package com.puce.gym.service;

import org.springframework.stereotype.Service;

import com.puce.gym.repository.PartnerRepository;

@Service 
public class PartnerService {
    public PartnerRepository partnerRepository;
    
    private PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }
    
}
