package com.practice.demo.controller;

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

	
	@GetMapping("/events/{clubId}/new")
	public String createEventForm(@PathVariable("clubId")Long clubId, Model model) {
		Event event = new Event();
		model.addAttribute("clubId", clubId);
		model.addAttribute("event", event);
		return "events-create";
	}
	
	@PostMapping("/events/{clubId}")
	public String createEvent(@PathVariable("clubId")Long clubId, @ModelAttribute("event") EventDto eventDDto, Model model) {

		eventService.createEvent(clubId, eventDDto);
		return "redirect:/clubs/"+clubId;
	}
	
	
}
