package com.zippi.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zippi.model.Medicalstore;

public interface MedicalstoreRepository extends JpaRepository<Medicalstore, Long> {

	Medicalstore findByName(String name);

//	@Query("SELECT r FROM Medicalstore r WHERE r.owner.id=:userId")
	List<Medicalstore> findMedicalstoresByOwnerId(@Param("userId") Long userId);
	
	@Query("SELECT r FROM Medicalstore r WHERE r.owner.id=:userId")
	List<Medicalstore>findUsersMedicalstore(@Param("userId")Long userId);




}