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
import org.springframework.web.bind.annotation.RequestParam;

import com.practice.demo.dto.ClubDto;
import com.practice.demo.models.Club;
import com.practice.demo.service.ClubService;

import jakarta.validation.Valid;

@Controller
public class ClubController {

	private ClubService clubService;

	@Autowired
	public ClubController(ClubService clubService) {
		this.clubService = clubService;
	}

	
	/**
	 * @param model
	 * @return 目前所有的俱樂部
	 */
	@GetMapping("/clubs")
	public String listClubs(Model model) {
		List<ClubDto> clubs = clubService.findAllClubs();

		model.addAttribute("clubs", clubs);
		return "clubs-list";
	}
	
	
	@GetMapping("/clubs/{clubId}")
	public String clubDetail(@PathVariable("clubId") long clubId, Model model) {
		ClubDto clubDto = clubService.findClubById(clubId);

		model.addAttribute("club", clubDto);
		return "clubs-detail";
	}

	@GetMapping("/clubs/new")
	public String createClubForm(Model model) {
		Club club = new Club();
		model.addAttribute("club", club);
		return "clubs-create";
	}

	@PostMapping("/clubs/new")
	public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDDto, BindingResult validResult, Model model) {

		// 驗證錯誤
		if (validResult.hasErrors()) {
			model.addAttribute("club",clubDDto);
			return "clubs-create";
		}

		clubService.saveClub(clubDDto);
		return "redirect:/clubs";
	}

	@GetMapping("/clubs/{clubId}/edit")
	public String editClubForm(@PathVariable("clubId") long clubId, Model model) {
		ClubDto club = clubService.findClubById(clubId);
		model.addAttribute("club", club);
		return "clubs-edit";
	}

	@PostMapping("/clubs/{clubId}/edit")
	public String updateClub(@PathVariable("clubId") long clubId, @Valid @ModelAttribute("club") ClubDto clubDto,
			BindingResult validResult, Model model) {

		// 驗證錯誤
		if (validResult.hasErrors()) {
			model.addAttribute("club", clubDto);
			return "clubs-edit";
		}

		clubDto.setId(clubId);
		clubService.updateClub(clubDto);
		return "redirect:/clubs";
	}
	
	
	@GetMapping("/clubs/{clubId}/delete")
	public String deleteClub(@PathVariable("clubId") long clubId) {
		clubService.delete(clubId);
		return "redirect:/clubs";
	}
	
	
	/**
	 * 依照輸入的字串，找出符合字串的俱樂部
	 * @param query
	 * 查詢的字串
	 * @param model
	 * @return 符合查詢的俱樂部
	 */
	@GetMapping("/clubs/search")
	public String searchClubs(@RequestParam(value = "query") String query, Model model) {
		
		List<ClubDto> clubDtos = clubService.searchClubs(query);
		model.addAttribute("clubs", clubDtos);
		return "clubs-list";
	}

}
