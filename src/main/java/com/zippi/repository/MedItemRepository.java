package com.zippi.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zippi.model.Category;
import com.zippi.model.MedItem;

public interface MedItemRepository extends JpaRepository<MedItem, Long> {

	Optional<List<MedItem>> findByCategory(Category category);

	@Query("SELECT m FROM MedItem m " + "WHERE (:isVegetarian IS NULL OR m.isVegetarian = :isVegetarian) "
			+ "AND (:isVegan IS NULL OR m.isVegan = :isVegan) "
			+ "AND (:isGlutenFree IS NULL OR m.isGlutenFree = :isGlutenFree)")
	Optional<List<MedItem>> getMedItemsByFilter(boolean isVegetarian, boolean isVegan, boolean isGlutenFree);

	Optional<MedItem> findByName(String name);
	
	@Query("SELECT mi FROM MedItem mi WHERE mi.medicalstore.id = :medicalstoreId")
	Optional<List<MedItem>> findByMedicalstoreId(@Param("medicalstoreId") Long medicalstoreId);
	
	@Query("SELECT m FROM MedItem m WHERE m.name LIKE %:keyword% OR m.category.name LIKE %:keyword%")
	List<MedItem> searchByNameOrCategory(@Param("keyword") String keyword);


	

}
