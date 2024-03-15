package com.zippi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zippi.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
	PasswordResetToken findByToken(String token);
}
