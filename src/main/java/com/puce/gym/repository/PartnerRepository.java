package com.puce.gym.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.puce.gym.models.Partner;

@Repository 
public interface PartnerRepository extends JpaRepository<Partner, Integer> {
Optional<Partner> findById(int id);
List<Partner> findByIsActive(boolean isActive);
List<Partner> findByPlanIgnoreCase(String plan);
}
