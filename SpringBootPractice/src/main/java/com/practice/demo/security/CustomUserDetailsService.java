package com.practice.demo.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.demo.models.UserEntity;
import com.practice.demo.repository.UserRepository;



//CustomUserDetailsService implements UserDetailsService		->	告訴 Spring Security：我要自己實作用戶查詢邏輯
//loadUserByUsername(...)					 					->	被 Spring Security呼叫來找出登入帳號的詳細資訊
//User(...)														->	把你資料庫裡的資訊包裝成 Spring Security 能認得的格式
//SimpleGrantedAuthority 										->	Spring 用來判斷權限的標準物件，例如 ROLE_ADMIN、ROLE_USER




@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findFirstByUsername(username);
		
		if(user != null) {
			User authUser = new User(
					user.getUsername(),
					user.getPassword(),
					user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
						.collect(Collectors.toList())
					);
			
			return authUser;
		}else {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		
	}
	
	
	
	
	
	
}
