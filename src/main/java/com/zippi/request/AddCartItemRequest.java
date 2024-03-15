package com.zippi.request;

import com.zippi.model.MedItem;

import lombok.Data;

@Data
public class AddCartItemRequest {
	
	private Long medItemId;
	private int quantity;

}