package com.zippi.request;
import com.zippi.request.UpdateCartItemRequest;

import lombok.Data;

@Data
public class UpdateCartItemRequest {
	
	private Long cartItemId;
	private int quantity;

}
