package com.practice.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.practice.demo.dto.ClubDto;
import com.practice.demo.dto.EventDto;
import com.practice.demo.models.Event;
import com.practice.demo.service.EventService;

import jakarta.validation.Valid;

@Controller
public class EventController {

	private EventService eventService;

	@Autowired
	public EventController(EventService eventService) {

		this.eventService = eventService;
	}

	@GetMapping("/events")
	public String listEvents(Model model) {
		List<EventDto> eventDtos = eventService.findAllEvents();
		model.addAttribute("events", eventDtos);
		return "events-list";
	}

	@GetMapping("/events/{eventId}")
	public String veiwEvent(@PathVariable("eventId") Long eventId, Model model) {
		EventDto eventDto = eventService.findEventById(eventId);
		model.addAttribute("eventId", eventId);
		model.addAttribute("event", eventDto);
		return "events-detail";
	}

	@GetMapping("/events/{clubId}/new")
	public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
		Event event = new Event();
		model.addAttribute("clubId", clubId);
		model.addAttribute("event", event);
		return "events-create";
	}

	@PostMapping("/events/{clubId}")
	public String createEvent(@PathVariable("clubId") Long clubId, @Valid @ModelAttribute("event") EventDto eventDDto,
			BindingResult validResult, Model model) {

		// 驗證錯誤
		if (validResult.hasErrors()) {
			model.addAttribute("event", eventDDto);
			return "events-create";
		}

		eventService.createEvent(clubId, eventDDto);
		return "redirect:/clubs/" + clubId;
	}

	@GetMapping("/events/{eventId}/edit")
	public String editClubForm(@PathVariable("eventId") long eventId, Model model) {
		EventDto event = eventService.findEventById(eventId);
		model.addAttribute("event", event);
		return "events-edit";
	}

	@PostMapping("/events/{eventId}/edit")
	public String updateEvent(@PathVariable("eventId") long eventId, @Valid @ModelAttribute("event") EventDto event,
			BindingResult validResult, Model model) {

		// 驗證錯誤
		if (validResult.hasErrors()) {
			model.addAttribute("event", event);
			return "events-edit";
		}

		EventDto eventDtoFromDatabase = eventService.findEventById(eventId);
		event.setId(eventId);
		event.setClub(eventDtoFromDatabase.getClub());
		eventService.updateEvent(event);
		return "redirect:/events/" + eventId;
	}
	
	@GetMapping("/events/{eventId}/delete")
	public String deleteEvent(@PathVariable("eventId") long eventId) {
		eventService.deleteEvent(eventId);
		return "redirect:/events";
	}

}
