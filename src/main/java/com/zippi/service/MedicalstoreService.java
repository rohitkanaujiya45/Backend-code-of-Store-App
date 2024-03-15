package com.zippi.service;

import java.util.List;

import com.zippi.Exception.MedicalstoreException;
import com.zippi.dto.MedicalstoreDto;
import com.zippi.model.Medicalstore;
import com.zippi.model.User;
import com.zippi.request.CreateMedicalstoreRequest;

public interface MedicalstoreService {

	public Medicalstore createMedicalstore(CreateMedicalstoreRequest req,User user);

	public Medicalstore updateMedicalstore(Long MedicalstoreId, CreateMedicalstoreRequest updatedMedicalstore)
			throws MedicalstoreException;

	public void deleteMedicalstore(Long MedicalstoreId) throws MedicalstoreException;

	public Medicalstore findMedicalstoreByName(String Name) throws MedicalstoreException;

	public List<Medicalstore> getMedicalstoresByName(String name);
	
	public List<Medicalstore>getAllMedicalstore();
	
	public Medicalstore findMedicalstoreById(Long id) throws MedicalstoreException;
//	 public  List<Medicalstore> getMedicalstoresByLocation(String location);
	public List<Medicalstore> getMedicalstoresByUserId(Long userId) throws MedicalstoreException;
	
	public MedicalstoreDto addToFavorites(Long MedicalstoreId,User user) throws MedicalstoreException;

}
