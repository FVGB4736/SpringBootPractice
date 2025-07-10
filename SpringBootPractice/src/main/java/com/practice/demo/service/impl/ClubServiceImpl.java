package com.practice.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.demo.dto.ClubDto;
import com.practice.demo.mapper.ClubMapper;
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
	            .map(club -> ClubMapper.mapToClubDto(club))
	            .collect(Collectors.toList());
	}
	

	@Override
	public Club saveClub(ClubDto clubDto) {
		Club club = ClubMapper.mapToClub(clubDto);
		return clubRepository.save(club);
	}

	@Override
	public ClubDto findClubById(long clubId) {
		Club club = clubRepository.findById(clubId).get();
		return ClubMapper.mapToClubDto(club);
	}

	@Override
	public void updateClub(ClubDto clubDto) {
		Club club = ClubMapper.mapToClub(clubDto);
		clubRepository.save(club);
		
	}


	@Override
	public void delete(long clubId) {
		clubRepository.deleteById(clubId);
		
	}

	
	@Override
	public List<ClubDto> searchClubs(String query) {
		
		List<Club> clubs = clubRepository.searchClubs(query);
		List<ClubDto> clubDtos = clubs.stream()
        .map(club -> ClubMapper.mapToClubDto(club))
        .collect(Collectors.toList());
		
		return clubDtos;
	}

}
