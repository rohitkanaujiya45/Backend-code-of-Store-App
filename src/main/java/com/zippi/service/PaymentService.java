package com.zippi.service;

import com.stripe.exception.StripeException;
import com.zippi.model.Order;
import com.zippi.model.PaymentResponse;

public interface PaymentService {
	
	public PaymentResponse generatePaymentLink(Order order) throws StripeException;

}
