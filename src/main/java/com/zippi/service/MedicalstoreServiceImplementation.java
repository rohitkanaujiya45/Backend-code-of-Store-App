package com.zippi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zippi.Exception.MedicalstoreException;
import com.zippi.dto.MedicalstoreDto;
import com.zippi.model.Address;
import com.zippi.model.Medicalstore;
import com.zippi.model.User;
import com.zippi.repository.AddressRepository;
import com.zippi.repository.MedicalstoreRepository;
import com.zippi.repository.UserRepository;
import com.zippi.request.CreateMedicalstoreRequest;

@Service
public class MedicalstoreServiceImplementation implements MedicalstoreService {
	@Autowired
	private MedicalstoreRepository medicalstoreRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public Medicalstore createMedicalstore(CreateMedicalstoreRequest req,User user) {
		Address address=new Address();
		address.setCity(req.getAddress().getCity());
		address.setCountry(req.getAddress().getCountry());
		address.setFullName(req.getAddress().getFullName());
		address.setPostalCode(req.getAddress().getPostalCode());
		address.setState(req.getAddress().getState());
		address.setStreetAddress(req.getAddress().getStreetAddress());
		Address savedAddress = addressRepository.save(address);
		
		Medicalstore medicalstore = new Medicalstore();
		
		medicalstore.setAddress(savedAddress);
		medicalstore.setContactInformation(req.getContactInformation());
		medicalstore.setCuisineType(req.getCuisineType());
		medicalstore.setDescription(req.getDescription());
		medicalstore.setImageUrl(req.getImageUrl());
		medicalstore.setName(req.getName());
		medicalstore.setOpeningHours(req.getOpeningHours());
		medicalstore.setRegistrationDate(req.getRegistrationDate());
		medicalstore.setOwner(user);
		Medicalstore savedMedicalstore = medicalstoreRepository.save(medicalstore);

		return savedMedicalstore;
	}

	@Override
	public Medicalstore updateMedicalstore(Long medicalstoreId, CreateMedicalstoreRequest updatedReq)
			throws MedicalstoreException {
		Medicalstore medicalstore = findMedicalstoreById(medicalstoreId);
		if (medicalstore.getCuisineType() != null) {
			medicalstore.setCuisineType(updatedReq.getCuisineType());
		}
		if (medicalstore.getDescription() != null) {
			medicalstore.setDescription(updatedReq.getDescription());
		}
		return medicalstoreRepository.save(medicalstore);
	}
	
	@Override
	public Medicalstore findMedicalstoreById(Long medicalstoreId) throws MedicalstoreException {
		Optional<Medicalstore> medicalstore = medicalstoreRepository.findById(medicalstoreId);
		if (medicalstore.isPresent()) {
			return medicalstore.get();
		} else {
			throw new MedicalstoreException("Medicalstore with id " + medicalstoreId + "not found");
		}
	}

	@Override
	public void deleteMedicalstore(Long medicalstoreId) throws MedicalstoreException {
		Medicalstore medicalstore = findMedicalstoreById(medicalstoreId);
		if (medicalstore != null) {
			medicalstoreRepository.delete(medicalstore);
			return;
		}
		throw new MedicalstoreException("Medicalstore with id " + medicalstoreId + " Not found");

	}

	@Override
	public List<Medicalstore> getMedicalstoresByName(String name) {

		return medicalstoreRepository.findAll();
	}

	@Override
	public Medicalstore findMedicalstoreByName(String Name) throws MedicalstoreException {
		Medicalstore medicalstore=medicalstoreRepository.findByName(Name);
		if(medicalstore!=null) return medicalstore;
		throw new MedicalstoreException("Medicalstore with name "+Name+"not found");
	}

	@Override
	public List<Medicalstore> getMedicalstoresByUserId(Long userId) throws MedicalstoreException {
		List<Medicalstore> medicalstores=medicalstoreRepository.findUsersMedicalstore(userId);
		return medicalstores;
	}

	public List<Medicalstore> getAllMedicalstore() {
		
		return medicalstoreRepository.findAll();

	}

	@Override
	public MedicalstoreDto addToFavorites(Long medicalstoreId,User user) throws MedicalstoreException {
		Medicalstore medicalstore=findMedicalstoreById(medicalstoreId);
		
		MedicalstoreDto dto=new MedicalstoreDto();
		dto.setTitle(medicalstore.getName());
		dto.setImageUrl(medicalstore.getImageUrl());
		dto.setId(medicalstore.getId());
		dto.setDescription(medicalstore.getDescription());
		
		if(user.getFavorites().contains(dto)) {
			user.getFavorites().remove(dto);
		}
		else user.getFavorites().add(dto);
		
		User updatedUser = userRepository.save(user);
		return dto;
	}

}
