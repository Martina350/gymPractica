package com.puce.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.puce.gym.models.Partner;

@Repository 
public interface PartnerRepository extends JpaRepository<Partner, Integer> {

}
