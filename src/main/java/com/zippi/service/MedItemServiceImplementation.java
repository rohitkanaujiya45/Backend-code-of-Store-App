package com.zippi.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zippi.Exception.MedItemException;
import com.zippi.Exception.MedicalstoreException;
import com.zippi.model.Category;
import com.zippi.model.MedItem;
import com.zippi.model.Medicalstore;
import com.zippi.repository.CategoryRepository;
import com.zippi.repository.MedItemRepository;
import com.zippi.repository.MedicalstoreRepository;
import com.zippi.request.CreateMedItemRequest;

@Service
public class MedItemServiceImplementation implements MedItemService {
	@Autowired
	private MedItemRepository medItemRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MedicalstoreRepository medicalstoreRepository;

	@Override
	public MedItem createMedItem(CreateMedItemRequest  req) throws MedItemException, 
	MedicalstoreException {
		if(true)
//		return new MedItem();
		if (req!=null) {

			Category newCategory =new Category();
			newCategory.setName(req.getCategory());
			Category savedCategory = categoryRepository.save(newCategory);
			System.out.println("Category object----"+newCategory);
			System.out.println("request object----"+req);
			
			MedItem medItem=new MedItem();
			medItem.setCategory(savedCategory);
			medItem.setCreationDate(new Date());
			medItem.setDescription(req.getDescription());
			medItem.setGlutenFree(req.isGlutenFree());
			medItem.setAvailabilityStatus(req.isAvailabilityStatus());
			medItem.setImageUrl(req.getImageUrl());
			medItem.setName(req.getName());
			medItem.setPrice((long) req.getPrice());
			medItem.setVegan(req.isVegan());
			medItem.setVegetarian(req.isVegetarian());
			
			Long medicalstoreId=req.getMedicalstoreId();
			
			Optional<Medicalstore> opt = medicalstoreRepository.findById(medicalstoreId);
//			System.out.println("Medicalstore object--"+medicalstoreId);
			if(opt.isPresent()) {
				medItem.setMedicalstore(opt.get());
				
			}else {
				throw new MedicalstoreException("Medicalstore not found with id "+medicalstoreId);
			}
			
			return medItemRepository.save(medItem);
		}

		throw new MedItemException("Item not saved");
	}

	@Override
	public void deleteMedItem(Long itemId) {

	}

	public MedItem findMedItemById(Long id) throws MedItemException {
		Optional<MedItem> medItem = medItemRepository.findById(id);
		if (medItem.isPresent()) {
			return medItem.get();
		}
		throw new MedItemException("MedItem with id" + id + "not found");
	}

	@Override
	public MedItem updateMedItem(Long itemId, MedItem updatedMedItem) throws MedItemException {
		MedItem medItem = findMedItemById(itemId);
		if (medItem.getCategory() != null) {
			medItem.setCategory(updatedMedItem.getCategory());
		}
		if (medItem.getDescription() != null) {
			medItem.setDescription(updatedMedItem.getDescription());
		}
		return medItemRepository.save(medItem);
	}

	@Override
	public List<MedItem> getMedItemsByCategory(Category category) throws MedItemException {
		Optional<List<MedItem>> medItems = medItemRepository.findByCategory(category);
		if (medItems.isPresent()) {
			return medItems.get();
		}
		throw new MedItemException("no items in the category" + category);
	}

	@Override
	public List<MedItem> getMedItemsByCriteria(boolean isVegetarian, boolean isVegan, boolean isGlutenFree)
			throws MedItemException {
		Optional<List<MedItem>> medItems = medItemRepository.getMedItemsByFilter(isVegetarian, isVegan,
				isGlutenFree);
		if (medItems.isPresent()) {
			return medItems.get();
		}
		throw new MedItemException("no items in the filter");
	}

	@Override
	public MedItem findMedItemByName(String name) throws MedItemException {
		Optional<MedItem> medItem=medItemRepository.findByName(name);
		if(medItem.isPresent()) {
			return medItem.get();
		}
		throw new MedItemException("Med item Not found");
	}

	@Override
	public List<MedItem> getMedicalstoreMedItems(Long medicalstoreId) throws MedItemException {
		Optional<List<MedItem>> medItems = medItemRepository.findByMedicalstoreId(medicalstoreId);
		if(medItems.isPresent()) {
			return medItems.get();
		}
		throw new MedItemException("Med item Not found");
		
	}

	@Override
	public List<MedItem> searchMedItem(String keyword) {
		List<MedItem> items=new ArrayList<>();
		
		if(keyword!="") {
			System.out.println("keyword -- "+keyword);
			items=medItemRepository.searchByNameOrCategory(keyword);
		}
		
		return items;
	}

}
