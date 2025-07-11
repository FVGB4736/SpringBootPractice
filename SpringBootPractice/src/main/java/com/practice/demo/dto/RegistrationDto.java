package com.practice.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
	private Long id;
	@NotEmpty(message = "User Name should not be Empty")
	private String username;
	@NotEmpty(message = "Email should not be Empty")
	private String email;
	@NotEmpty(message = "Password should not be Empty")
	private String password;
}
