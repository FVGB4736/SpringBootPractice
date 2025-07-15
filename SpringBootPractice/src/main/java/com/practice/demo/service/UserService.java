package com.practice.demo.service;

import com.practice.demo.dto.RegistrationDto;
import com.practice.demo.models.UserEntity;

public interface UserService {
	void saveUser(RegistrationDto registrationDto);
	
	UserEntity findByEmail(String email);

	UserEntity findByUsername(String username);
}
