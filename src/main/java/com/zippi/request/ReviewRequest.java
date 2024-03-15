package com.zippi.request;
import com.zippi.request.ReviewRequest;

import lombok.Data;

@Data
public class ReviewRequest {

    private Long medicalstoreId;
    
    private double rating;
    
    private String reviewText;

	
}
