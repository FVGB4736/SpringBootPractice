package com.practice.demo.service;

import com.practice.demo.dto.EventDto;


public interface EventService {
	void createEvent(Long clubId, EventDto eventDto);
}
