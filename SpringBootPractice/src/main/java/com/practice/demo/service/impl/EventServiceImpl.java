package com.practice.demo.service.impl;

import org.springframework.stereotype.Service;

import com.practice.demo.dto.EventDto;
import com.practice.demo.mapper.EventMapper;
import com.practice.demo.models.Club;
import com.practice.demo.models.Event;
import com.practice.demo.repository.ClubRepository;
import com.practice.demo.repository.EventRepository;
import com.practice.demo.service.EventService;

@Service
public class EventServiceImpl implements EventService{
	
	
	public EventServiceImpl(ClubRepository clubRepository, EventRepository eventRepository) {
		super();
		this.clubRepository = clubRepository;
		this.eventRepository = eventRepository;
	}

	private ClubRepository clubRepository;
	private EventRepository eventRepository;
	
	@Override
	public void createEvent(Long clubId, EventDto eventDto) {
		Club club = clubRepository.findById(clubId).get();
		Event event = EventMapper.mapToEvent(eventDto);
		event.setClub(club);
		eventRepository.save(event);
		
	}
	
	

}
