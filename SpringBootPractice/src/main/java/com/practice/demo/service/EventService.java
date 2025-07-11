package com.practice.demo.service;

import java.util.List;

import com.practice.demo.dto.EventDto;

import jakarta.validation.Valid;


public interface EventService {
	void createEvent(Long clubId, EventDto eventDto);
	
	List<EventDto> findAllEvents();

	EventDto findEventById(Long eventId);

	void updateEvent(EventDto eventDto);

	void deleteEvent(long eventId);
}
