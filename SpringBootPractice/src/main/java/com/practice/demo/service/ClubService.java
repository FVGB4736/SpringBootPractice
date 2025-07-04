package com.practice.demo.service;

import java.util.List;

import com.practice.demo.dto.ClubDto;
import com.practice.demo.models.Club;

public interface ClubService {
	List<ClubDto> findAllClubs();
	
	Club saveClub(Club club);

	ClubDto findClubById(long clubId);
}
