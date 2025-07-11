package com.practice.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.demo.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);
	UserEntity findByUsername(String userName);

}
