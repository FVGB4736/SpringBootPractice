package com.practice.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.practice.demo.dto.EventDto;
import com.practice.demo.mapper.EventMapper;
import com.practice.demo.models.Club;
import com.practice.demo.models.Event;
import com.practice.demo.repository.ClubRepository;
import com.practice.demo.repository.EventRepository;
import com.practice.demo.service.EventService;

import jakarta.validation.Valid;

@Service
public class EventServiceImpl implements EventService{
	
	private ClubRepository clubRepository;
	private EventRepository eventRepository;
	
	public EventServiceImpl(ClubRepository clubRepository, EventRepository eventRepository) {
		super();
		this.clubRepository = clubRepository;
		this.eventRepository = eventRepository;
	}

	
	
	@Override
	public void createEvent(Long clubId, EventDto eventDto) {
		Club club = clubRepository.findById(clubId).get();
		Event event = EventMapper.mapToEvent(eventDto);
		event.setClub(club);
		eventRepository.save(event);
		
	}

	@Override
	public List<EventDto> findAllEvents() {
		List<Event> events = eventRepository.findAll();
		
		List<EventDto> eventDtos = events.stream()
				.map(event -> EventMapper.mapToEventDto(event))
				.collect(Collectors.toList());
		return eventDtos;
	}

	
	@Override
	public EventDto findEventById(Long eventId) {
		Event event = eventRepository.findById(eventId).get();
		return EventMapper.mapToEventDto(event);
	}

	@Override
	public void updateEvent(EventDto eventDto) {
		Event event = EventMapper.mapToEvent(eventDto);
		eventRepository.save(event);
		
	}

	@Override
	public void deleteEvent(long eventId) {
		eventRepository.deleteById(eventId);
		
	}
	
	

}
