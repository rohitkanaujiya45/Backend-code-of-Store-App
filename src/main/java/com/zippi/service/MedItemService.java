package com.zippi.service;

import java.util.List;

import com.zippi.Exception.MedItemException;
import com.zippi.Exception.MedicalstoreException;
import com.zippi.model.Category;
import com.zippi.model.MedItem;
import com.zippi.request.CreateMedItemRequest;

public interface MedItemService {

	public MedItem createMedItem(CreateMedItemRequest req) throws MedItemException, MedicalstoreException;

	public MedItem updateMedItem(Long itemId, MedItem updatedMedItem) throws MedItemException;

	void deleteMedItem(Long itemId);

	public List<MedItem> getMedItemsByCategory(Category category) throws MedItemException;

	public List<MedItem> getMedItemsByCriteria(boolean isVegetarian, boolean isVegan, boolean isGlutenFree)
			throws MedItemException;

	public MedItem findMedItemByName(String string) throws MedItemException;
	
	public List<MedItem> getMedicalstoreMedItems(Long medicalstoreId) throws MedItemException;
	
	public List<MedItem> searchMedItem(String keyword);

}
