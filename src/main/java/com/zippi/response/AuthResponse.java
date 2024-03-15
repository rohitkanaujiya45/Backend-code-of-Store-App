package com.zippi.response;

import com.zippi.response.AuthResponse;

import lombok.Data;

@Data
public class AuthResponse {
	
	private String message;
	private String jwt;
	


}