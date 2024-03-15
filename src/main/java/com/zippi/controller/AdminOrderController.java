package com.zippi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.zippi.Exception.CartException;
import com.zippi.Exception.OrderException;
import com.zippi.Exception.MedicalstoreException;
import com.zippi.Exception.UserException;
import com.zippi.model.Order;
import com.zippi.model.PaymentResponse;
import com.zippi.model.User;
import com.zippi.request.CreateOrderRequest;
import com.zippi.service.OrderService;
import com.zippi.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	
    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) throws OrderException{
    	if(orderId!=null) {
    		orderService.cancelOrder(orderId);
    	return ResponseEntity.ok("Order deleted with id)"+orderId);
    }else return new ResponseEntity<String>(HttpStatus.BAD_REQUEST) ;
    }
    
    
    @GetMapping("/order/medicalstore/{medicalstoreId}")
    public ResponseEntity<List<Order>> getAllMedicalstoreOrders(@PathVariable Long medicalstoreId) throws OrderException, MedicalstoreException{
    	if(medicalstoreId!=null) {
    		List<Order> orders = orderService.getOrdersOfMedicalstore(medicalstoreId);
    		return ResponseEntity.ok(orders);
    		
    	}
    	else {
    		return new ResponseEntity<List<Order>>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @PutMapping("/orders/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrders(@PathVariable Long orderId,@PathVariable String orderStatus) throws OrderException, MedicalstoreException{
    	
    		Order orders = orderService.updateOrder(orderId, orderStatus);
    		return ResponseEntity.ok(orders);
    		
    }

}
