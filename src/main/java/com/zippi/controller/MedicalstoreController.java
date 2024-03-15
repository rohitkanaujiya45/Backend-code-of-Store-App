package com.zippi.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.zippi.Exception.MedicalstoreException;
import com.zippi.Exception.UserException;
import com.zippi.domain.Role;
import com.zippi.dto.MedicalstoreDto;
import com.zippi.model.Medicalstore;
import com.zippi.model.User;
import com.zippi.repository.MedicalstoreRepository;
import com.zippi.repository.UserRepository;
import com.zippi.request.CreateMedicalstoreRequest;
import com.zippi.response.ApiResponse;
import com.zippi.service.MedicalstoreService;
import com.zippi.service.UserService;

@RestController
@RequestMapping("/api")
public class MedicalstoreController {
	
	@Autowired
	private MedicalstoreService medicalstoreService;
	
	@Autowired
	private UserService userService;


	@GetMapping("/medicalstore/search/{name}")
	public ResponseEntity<Medicalstore> findMedicalstoreByName(@PathVariable String name) throws MedicalstoreException {
		Medicalstore medicalstore = medicalstoreService.findMedicalstoreByName(name);

		return ResponseEntity.ok(medicalstore);
	}


	@GetMapping("/medicalstores")
	public ResponseEntity<List<Medicalstore>> getAllMedicalstores() throws MedicalstoreException, UserException {

		List<Medicalstore> medicalstores = medicalstoreService.getAllMedicalstore();
		
		
		return ResponseEntity.ok(medicalstores);
	}
	
	
	@GetMapping("/medicalstore/{id}")
	public ResponseEntity<Medicalstore> findMedicalstoreById(@PathVariable Long id) throws MedicalstoreException, UserException {
		
	
			Medicalstore medicalstore = medicalstoreService.findMedicalstoreById(id);
			return ResponseEntity.ok(medicalstore);
		
	
	}
	
	@PutMapping("/medicalstore/{id}/add-favorites")
	public ResponseEntity<MedicalstoreDto> addToFavorite(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws MedicalstoreException, UserException {
		
		User user = userService.findUserProfileByJwt(jwt);
			MedicalstoreDto medicalstore = medicalstoreService.addToFavorites(id, user);
			return ResponseEntity.ok(medicalstore);
		
	
	}


}