package com.zippi.request;


import com.zippi.model.Address;

import lombok.Data;

@Data
public class CreateOrderRequest {
 
	private Long medicalstoreId;
	
	private Address deliveryAddress;
	
    
}
