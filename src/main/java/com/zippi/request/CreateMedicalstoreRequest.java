package com.zippi.request;

import java.time.LocalDateTime;

import com.zippi.model.Address;
import com.zippi.model.ContactInformation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMedicalstoreRequest {

	private Long id;
	private String name;
	private String description;
	private String cuisineType;
	private Address address;
	private ContactInformation contactInformation;
	private String openingHours;
	private String imageUrl;
    private LocalDateTime registrationDate;
}
