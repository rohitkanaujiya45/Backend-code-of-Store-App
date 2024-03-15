package com.zippi.model;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User customer;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "medicalstore_id")
	private Medicalstore medicalstore;

	private Long totalAmount;
	private String orderStatus;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@ManyToOne
	@JoinColumn(name = "delivery_address_id")
	private Address deliveryAddress;

//	@JsonIgnore
	@OneToMany
//	(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> items;

	@OneToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	private int totalItem;
	
	private int total_price;

}