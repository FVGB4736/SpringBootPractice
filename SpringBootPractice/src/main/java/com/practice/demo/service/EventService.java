package com.practice.demo.service;

import java.util.List;

import com.practice.demo.dto.EventDto;


public interface EventService {
	void createEvent(Long clubId, EventDto eventDto);
	
	List<EventDto> findAllEvents();

	EventDto findEventById(Long eventId);
}
