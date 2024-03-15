package com.zippi.request;
import com.zippi.request.ResetPasswordRequest;

import lombok.Data;

@Data
public class ResetPasswordRequest {
	
	private String password;
	private String token;


}
