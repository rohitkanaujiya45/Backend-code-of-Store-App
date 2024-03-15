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
@RequestMapping("/api")
public class MedItemController {
	@Autowired
	private MedItemService medItemService;
	@Autowired
	private UserService userService;

	@GetMapping("/med/filter")
	public ResponseEntity<List<MedItem>> getItemByFilter(@RequestParam boolean isVegetarian,
			@RequestParam boolean isVegan, @RequestParam boolean isGlutenFree) throws MedItemException {
		List<MedItem> itemsByCriteria = medItemService.getMedItemsByCriteria(isVegetarian, isVegan, isGlutenFree);
		return ResponseEntity.ok(itemsByCriteria);
	}

	@GetMapping("/med/search")
	public ResponseEntity<List<MedItem>> getMedItemByName(@RequestParam String name)  {
		List<MedItem> medItem = medItemService.searchMedItem(name);
		return ResponseEntity.ok(medItem);
	}
	@GetMapping("/med/medicalstore/{medicalstoreId}")
	public ResponseEntity<List<MedItem>> getMedItemByMedicalstoreId(@PathVariable Long medicalstoreId) throws MedItemException {
		List<MedItem> medItems= medItemService.getMedicalstoreMedItems(medicalstoreId);
		return ResponseEntity.ok(medItems);
	}

}
