package com.zippi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zippi.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}

