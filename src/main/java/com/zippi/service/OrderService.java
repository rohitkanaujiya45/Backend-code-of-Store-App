package com.zippi.service;

import java.util.List;

import com.stripe.exception.StripeException;
import com.zippi.Exception.CartException;
import com.zippi.Exception.OrderException;
import com.zippi.Exception.MedicalstoreException;
import com.zippi.Exception.UserException;
import com.zippi.model.Order;
import com.zippi.model.PaymentResponse;
import com.zippi.model.User;
import com.zippi.request.CreateOrderRequest;

public interface OrderService {
	
	 public PaymentResponse createOrder(CreateOrderRequest order, User user) throws UserException, MedicalstoreException, CartException, StripeException;
	 
	 public Order updateOrder(Long orderId, String orderStatus) throws OrderException;
	 
	 public void cancelOrder(Long orderId) throws OrderException;
	 
	 public List<Order> getUserOrders(Long userId) throws OrderException;
	 
	 public List<Order> getOrdersOfMedicalstore(Long medicalstoreId) throws OrderException, MedicalstoreException;
	 

}
