package com.zippi.model;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;
    private Long price;
    
    @ManyToOne
    private Category category;
    private String imageUrl;
    private boolean availabilityStatus;

    
    @ManyToOne
    @JoinColumn(name = "medicalstore_id")
    private Medicalstore medicalstore;
    
    private boolean isVegetarian;
    private boolean isGlutenFree;
    private boolean isVegan;
    
    private Long discountedPrice;
    
    private Integer discount;
    

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    }