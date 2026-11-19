package com.puce.gym.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puce.gym.service.PartnerService;

@RestController 
@RequestMapping ("/api/partners")
public class PartnerController {
    private PartnerService partnerService;
    
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

}
