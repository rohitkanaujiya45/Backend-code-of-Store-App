package com.zippi.service;

import java.util.List;

import com.zippi.Exception.ReviewException;
import com.zippi.model.Review;
import com.zippi.model.User;
import com.zippi.request.ReviewRequest;

public interface ReviewSerive {
	
    public Review submitReview(ReviewRequest review,User user);
    public void deleteReview(Long reviewId) throws ReviewException;
    public double calculateAverageRating(List<Review> reviews);
	public Review updateReview(Long reviewId, ReviewRequest updatedReview) throws ReviewException;

}
