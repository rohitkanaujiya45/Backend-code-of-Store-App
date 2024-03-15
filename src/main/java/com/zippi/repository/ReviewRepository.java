package com.zippi.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zippi.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
