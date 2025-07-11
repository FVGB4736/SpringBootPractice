package com.practice.demo.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.demo.dto.RegistrationDto;
import com.practice.demo.models.Role;
import com.practice.demo.models.UserEntity;
import com.practice.demo.repository.RoleRepository;
import com.practice.demo.repository.UserRepository;
import com.practice.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void saveUser(RegistrationDto registrationDto) {
		UserEntity user = new UserEntity();
		user.setUsername(registrationDto.getUsername());
		user.setPassword(registrationDto.getPassword());
		user.setEmail(registrationDto.getEmail());
		
		Role role = roleRepository.findByName("USER");
		user.setRoles(Arrays.asList(role));
		
		userRepository.save(user);
		
		
	}

}
