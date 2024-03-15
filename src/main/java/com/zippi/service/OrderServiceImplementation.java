package com.zippi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stripe.exception.StripeException;
import com.zippi.Exception.CartException;
import com.zippi.Exception.OrderException;
import com.zippi.Exception.MedicalstoreException;
import com.zippi.Exception.UserException;
import com.zippi.model.Address;
import com.zippi.model.Cart;
import com.zippi.model.CartItem;
import com.zippi.model.Order;
import com.zippi.model.OrderItem;
import com.zippi.model.PaymentResponse;
import com.zippi.model.Medicalstore;
import com.zippi.model.User;
import com.zippi.repository.AddressRepository;
import com.zippi.repository.CartRepository;
import com.zippi.repository.OrderItemRepository;
import com.zippi.repository.OrderRepository;
import com.zippi.repository.MedicalstoreRepository;
import com.zippi.repository.UserRepository;
import com.zippi.request.CreateOrderRequest;
@Service
public class OrderServiceImplementation implements OrderService {
	
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CartSerive cartService;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private MedicalstoreRepository medicalstoreRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PaymentService paymentSerive;
	

	

	@Override
	public PaymentResponse createOrder(CreateOrderRequest order,User user) throws UserException, MedicalstoreException, CartException, StripeException {
		
	    Address shippAddress = order.getDeliveryAddress();

	    
	    Address savedAddress = addressRepository.save(shippAddress);
	    
	    if(!user.getAddresses().contains(savedAddress)) {
	    	user.getAddresses().add(savedAddress);
	    }
	    
		
		System.out.println("user addresses --------------  "+user.getAddresses());
		   
		 userRepository.save(user);
	    
	    Optional<Medicalstore> medicalstore = medicalstoreRepository.findById(order.getMedicalstoreId());
	    if(medicalstore.isEmpty()) {
	    	throw new MedicalstoreException("Medicalstore not found with id "+order.getMedicalstoreId());
	    }
	    
	    Order createdOrder = new Order();
	    
	    createdOrder.setCustomer(user);
	    createdOrder.setDeliveryAddress(savedAddress);
	    createdOrder.setCreatedAt(new Date());
	    createdOrder.setOrderStatus("PENDING");
	    createdOrder.setMedicalstore(medicalstore.get());

        Cart cart = cartService.findCartByUserId(user.getId());
        
	    List<OrderItem> orderItems = new ArrayList<>();
	    
	    for (CartItem cartItem : cart.getItems()) {
	        OrderItem orderItem = new OrderItem();
	       orderItem.setMedItem(cartItem.getMedItem());
	       orderItem.setQuantity(cartItem.getQuantity());
	        orderItem.setSubtotal(cartItem.getMedItem().getPrice()* cartItem.getQuantity());

	        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
	        orderItems.add(savedOrderItem);
	    }
   
	     Long totalPrice = cartService.calculateCartTotals(cart);

	    createdOrder.setTotalAmount(totalPrice);
	    createdOrder.setMedicalstore(medicalstore.get());
  
	    createdOrder.setItems(orderItems);
	    Order savedOrder = orderRepository.save(createdOrder);

	   medicalstore.get().getOrders().add(savedOrder);
	   
	   medicalstoreRepository.save(medicalstore.get());
	   

	   
	   PaymentResponse res=paymentSerive.generatePaymentLink(savedOrder);
	   return res;

	}

	@Override
	public void cancelOrder(Long orderId) throws OrderException {
           Order order =findOrderById(orderId);
           if(order==null) {
        	   throw new OrderException("Order not found with the id "+orderId);
           }
		
		    orderRepository.deleteById(orderId);
		
	}
	
	public Order findOrderById(Long orderId) throws OrderException {
		Optional<Order> order = orderRepository.findById(orderId);
		if(order.isPresent()) return order.get();
		
		throw new OrderException("Order not found with the id "+orderId);
	}

	@Override
	public List<Order> getUserOrders(Long userId) throws OrderException {
		Optional<List<Order>> orders=orderRepository.findAllUserOrders(userId);
		if(orders.isPresent()) {
			return orders.get();
		}
		
		throw new OrderException("orders not found");
	}

	@Override
	public List<Order> getOrdersOfMedicalstore(Long medicalstoreId) throws OrderException, MedicalstoreException {
		 if(medicalstoreId!=null) {
			 Optional<List<Order>> orders = orderRepository.findOrdersByMedicalstoreId(medicalstoreId);
			 if(orders.isPresent()){
				 return orders.get();
			 }else {
				 throw new OrderException("Orders not found");
			 }
		 }
		 throw new MedicalstoreException("Medicalstore not found with the id "+medicalstoreId);
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws OrderException {
		Order order=findOrderById(orderId);
		
		System.out.println("--------- "+orderStatus);
		
		if(orderStatus.equals("PLACED") || orderStatus.equals("DELIVERED") 
				|| orderStatus.equals("CONFIRMED")) {
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}
		else throw new OrderException("Please Select A Valid Order Status");
		
		
	}
	
	

}
