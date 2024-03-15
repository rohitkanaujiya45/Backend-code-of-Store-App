package com.zippi.service;

import com.zippi.model.Order;
import com.zippi.model.Medicalstore;
import com.zippi.model.User;

public interface NotificationService {
	
	public void sendOrderStatusNotification(User user, Order order);
	public void sendMedicalstoreNotification(Medicalstore restaurant, String message);
	public void sendPromotionalNotification(User user, String message);

}
