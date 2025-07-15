package com.practice.demo.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private final PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void saveUser(RegistrationDto registrationDto) {
		UserEntity user = new UserEntity();
		user.setUsername(registrationDto.getUsername());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		user.setEmail(registrationDto.getEmail());
		
		Role role = roleRepository.findByName("USER");
		user.setRoles(Arrays.asList(role));
		
		userRepository.save(user);
		
		
	}

	@Override
	public UserEntity findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	@Override
	public UserEntity findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

}
