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
import com.zippi.model.Medicalstore;
import com.zippi.model.User;
import com.zippi.request.CreateMedicalstoreRequest;
import com.zippi.response.ApiResponse;
import com.zippi.service.MedicalstoreService;
import com.zippi.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminMedicalstoreController {
	@Autowired
	private MedicalstoreService medicalstoreService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/medicalstore")
	public ResponseEntity<Medicalstore> createMedicalstore(
			@RequestBody CreateMedicalstoreRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException {
		User user = userService.findUserProfileByJwt(jwt);
		
//			System.out.println("----TRUE___-----");
			Medicalstore medicalstore = medicalstoreService.createMedicalstore(req,user);
			return ResponseEntity.ok(medicalstore);
		
	}


	@PutMapping("/medicalstore/{id}")
	public ResponseEntity<Medicalstore> updateMedicalstore(@PathVariable Long id, @RequestBody CreateMedicalstoreRequest req,
			@RequestHeader("Authorization") String jwt) throws MedicalstoreException, UserException {
		User user = userService.findUserProfileByJwt(jwt);
		
			Medicalstore medicalstore = medicalstoreService.updateMedicalstore(id, req);
			return ResponseEntity.ok(medicalstore);
		
	}

	@DeleteMapping("/medicalstore/{id}")
	public ResponseEntity<ApiResponse> deleteMedicalstoreById(@PathVariable("id") Long medicalstoreId,
			@RequestHeader("Authorization") String jwt) throws MedicalstoreException, UserException {
		User user = userService.findUserProfileByJwt(jwt);
		
			medicalstoreService.deleteMedicalstore(medicalstoreId);
			
			ApiResponse res=new ApiResponse("Medicalstore Deleted with id Successfully",true);
			return ResponseEntity.ok(res);
	
		
	}

	
	@GetMapping("/medicalstores/user")
	public ResponseEntity<List<Medicalstore>> getUsersMedicalstore(@RequestHeader("Authorization") String jwt) throws UserException, MedicalstoreException {
		User user = userService.findUserProfileByJwt(jwt);
		List<Medicalstore> medicalstores = medicalstoreService.getMedicalstoresByUserId(user.getId());
		return ResponseEntity.ok(medicalstores);
	}
	
	
	

}