package com.zippi.service;

import java.util.List;

import com.zippi.Exception.UserException;
import com.zippi.model.User;

public interface UserService {

	public User findUserProfileByJwt(String jwt) throws UserException;
	
	public User findUserByEmail(String email) throws UserException;

	public List<User> findAllUsers();

	public List<User> getPenddingMedicalstoreOwner();

	void updatePassword(User user, String newPassword);

	void sendPasswordResetEmail(User user);

}
