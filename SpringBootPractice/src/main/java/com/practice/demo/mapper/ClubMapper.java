package com.practice.demo.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.practice.demo.dto.ClubDto;
import com.practice.demo.dto.EventDto;
import com.practice.demo.models.Club;
import com.practice.demo.models.Event;

public class ClubMapper {

	public static ClubDto mapToClubDto(Club club) {
		
		List<EventDto> eventDtos = new ArrayList<EventDto>();
		eventDtos = club.getEvents().stream()
				.map(event -> EventMapper.mapToEventDto(event))
				.collect(Collectors.toList());
		
		ClubDto clubDto = ClubDto.builder()
	            .id(club.getId())
	            .title(club.getTitle())
	            .photoUrl(club.getPhotoUrl())
	            .content(club.getContent())
	            .createdOn(club.getCreatedOn())
	            .updatedOn(club.getUpdatedOn())
	            .events(eventDtos)
	            .build();
		return clubDto;
	}
	
	public static Club mapToClub(ClubDto clubDto) {
		
		List<Event> events = new ArrayList<Event>();
		events = clubDto.getEvents().stream()
				.map(eventDto -> EventMapper.mapToEvent(eventDto))
				.collect(Collectors.toList());
		
		Club club = Club.builder()
	            .id(clubDto.getId())
	            .title(clubDto.getTitle())
	            .photoUrl(clubDto.getPhotoUrl())
	            .content(clubDto.getContent())
	            .createdOn(clubDto.getCreatedOn())
	            .updatedOn(clubDto.getUpdatedOn())
	            .events(events)
	            .build();
		return club;
	}
}
