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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zippi.Exception.MedItemException;
import com.zippi.Exception.MedicalstoreException;
import com.zippi.Exception.UserException;
import com.zippi.model.MedItem;
import com.zippi.model.User;
import com.zippi.request.CreateMedItemRequest;
import com.zippi.service.MedItemService;
import com.zippi.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminMedItemController {
	
	@Autowired
	private MedItemService medItemService;
	@Autowired
	private UserService userService;

	@PostMapping("/med")
	public ResponseEntity<MedItem> createItem(
			@RequestBody CreateMedItemRequest item, 
			@RequestHeader("Authorization") String jwt)
			throws MedItemException, UserException, MedicalstoreException {
		System.out.println("req-controller ----"+item);
		User user = userService.findUserProfileByJwt(jwt);
		

			MedItem medItem = medItemService.createMedItem(item);


			return ResponseEntity.ok(medItem);

	}

	@PutMapping("/med/{id}")
	public ResponseEntity<MedItem> updateItem(@PathVariable Long id, @RequestBody MedItem upddatedMedItem,
			@RequestHeader("Authorization") String jwt) throws MedItemException, UserException {
		User user = userService.findUserProfileByJwt(jwt);
		if (user.getRole().equals("ROLE_MEDICALSTORE_OWNER")) {
			MedItem updateMedItem = medItemService.updateMedItem(id, upddatedMedItem);
			return ResponseEntity.ok(updateMedItem);
		}
		throw new UserException("User not authorize to update menu");
	}

	@DeleteMapping("/med/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable Long id, @RequestHeader("Authorization") String jwt)
			throws UserException {
		User user = userService.findUserProfileByJwt(jwt);
		if (user.getRole().equals("ROLE_MEDICALSTORE_OWNER")) {
			medItemService.deleteMedItem(id);
			return ResponseEntity.ok("Menu item deleted");
		}
		throw new UserException("User not authorize to delete menu");
	}

	

	@GetMapping("/med/search")
	public ResponseEntity<List<MedItem>> getMedItemByName(@RequestParam String name)  {
		List<MedItem> medItem = medItemService.searchMedItem(name);
		return ResponseEntity.ok(medItem);
	}
	
	
	
	
	

}