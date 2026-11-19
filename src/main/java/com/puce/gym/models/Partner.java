package com.puce.gym.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "partner_id")
    private int id;
    @Column (name = "name")
    private String name;
    @Column (name = "plan")
    private String plan;
    @Column (name = "monthly_payment")
    private double monthlyPayment;
    @Column (name = "is_active")
    private boolean isActive;
}
