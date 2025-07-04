package com.practice.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.demo.dto.ClubDto;
import com.practice.demo.models.Club;
import com.practice.demo.repository.ClubRepository;
import com.practice.demo.service.ClubService;

@Service
public class ClubServiceImpl implements ClubService{

	
	private ClubRepository clubRepository;
	
	@Autowired
	public ClubServiceImpl(ClubRepository clubRepository) {
		this.clubRepository = clubRepository;
	}
	
	@Override
	public List<ClubDto> findAllClubs() {
		List<Club> clubs = clubRepository.findAll();
		return clubs.stream()
	            .map(club -> mapToClubDto(club))
	            .collect(Collectors.toList());
	}
	
	private ClubDto mapToClubDto(Club club) {
		ClubDto clubDto = ClubDto.builder()
	            .id(club.getId())
	            .title(club.getTitle())
	            .photoUrl(club.getPhotoUrl())
	            .content(club.getContent())
	            .createdOn(club.getCreatedOn())
	            .updatedOn(club.getUpdatedOn())
	            .build();
		return clubDto;
	}

	@Override
	public Club saveClub(Club club) {
		
		return clubRepository.save(club);
	}

	@Override
	public ClubDto findClubById(long clubId) {
		Club club = clubRepository.findById(clubId).get();
		return this.mapToClubDto(club);
	}

}
