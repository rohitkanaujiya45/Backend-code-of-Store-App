package com.zippi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zippi.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
