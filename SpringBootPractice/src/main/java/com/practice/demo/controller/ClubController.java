package com.practice.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.practice.demo.dto.ClubDto;
import com.practice.demo.models.Club;
import com.practice.demo.service.ClubService;

@Controller
public class ClubController {
	
	private ClubService clubService;

	@Autowired
	public ClubController(ClubService clubService) {
		this.clubService = clubService;
	}
	
	@GetMapping("/clubs")
	public String listClubs(Model model) {
		List<ClubDto> clubs = clubService.findAllClubs();
		
		model.addAttribute("clubs",clubs);
		return "clubs-list";
	}
	
	@GetMapping("/clubs/new")
	public String createClubForm(Model model) {
		Club club = new Club();
		model.addAttribute("club",club);
		return "clubs-create";
	}
	
	@PostMapping("/clubs/new")
	public String saveClub(@ModelAttribute("club") Club club) {
		clubService.saveClub(club);
		return "redirect:/clubs";
	}
	
	@GetMapping("/clubs/{clubId}/edit")
	public String editClubForm(@PathVariable("clubId") long clubId, Model model) {
		ClubDto club = clubService.findClubById(clubId);
		model.addAttribute("club", club);
		return "clubs-edit";
	}
	
	

}
