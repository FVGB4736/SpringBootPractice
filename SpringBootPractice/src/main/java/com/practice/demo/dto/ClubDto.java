package com.practice.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubDto {
	private Long id;
	private String title;
	private String photoUrl;
	private String content;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}
