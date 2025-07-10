package com.practice.demo.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.practice.demo.models.Club;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
	private long id;
	private String name;
	
	@DateTimeFormat(pattern="yyyy-mm-dd'T'hh:mm")
	private LocalDateTime startTime;
	@DateTimeFormat(pattern="yyyy-mm-dd'T'hh:mm")
	private LocalDateTime endTime;
	
	private String type;
	
	private String photoUrl;
	
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	private Club club;
}
