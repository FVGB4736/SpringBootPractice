package com.practice.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.practice.demo.dto.RegistrationDto;
import com.practice.demo.models.UserEntity;
import com.practice.demo.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	private UserService userService;

	@Autowired
	public AuthController(UserService userService) {
		
		this.userService = userService;
	}
	
	@GetMapping("/register")
	public String getRegisterForm(Model model) {
		RegistrationDto user = new RegistrationDto();
		model.addAttribute("user", user);
		return "register";
	}
	
	@PostMapping("/register/new")
	public String register(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult validResult, Model model) {

		//檢查資料庫中，是否已經有註冊過的電子郵件帳號的USER
		UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
		if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
			//validResult.rejectValue("email", "There is already a user with this email/username.");
			return "redirect:/register?fail";
		}
		
		//檢查資料庫中，是否已經有註冊過的使用者名稱
		UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
		if(existingUserUsername != null && existingUserUsername.getEmail() != null && !existingUserUsername.getEmail().isEmpty()) {
			//validResult.rejectValue("username", "There is already a user with this email/username.");
			return "redirect:/register?fail";
		}
		
		if (validResult.hasErrors()) {
			model.addAttribute("user",user);
			return "register";
		}
		
		userService.saveUser(user);
		return "redirect:/clubs?success";
		
	}
	
	@GetMapping("/login")
	public String logIn(Model model) {
		return "login";
	}
	
	

}
