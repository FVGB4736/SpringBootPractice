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
public class ClubDto {
	private Long id;
	@NotEmpty(message = "Club Title should not be Empty")
	private String title;
	@NotEmpty(message = "Photo Link should not be Empty")
	private String photoUrl;
	@NotEmpty(message = "Content should not be Empty")
	private String content;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	private List<EventDto> events;
}
