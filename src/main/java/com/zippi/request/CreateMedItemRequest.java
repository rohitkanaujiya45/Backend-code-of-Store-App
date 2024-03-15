package com.zippi.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMedItemRequest  {
	

    
    private String name;
    private String description;
    private Long price;
    
  
    private String category;
    private String imageUrl;
    private boolean availabilityStatus;

   
    private Long medicalstoreId;
    
    private boolean isVegetarian;
    private boolean isGlutenFree;
    private boolean isVegan;
	

}
