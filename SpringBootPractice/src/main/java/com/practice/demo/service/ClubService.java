package com.practice.demo.service;

import java.util.List;

import com.practice.demo.dto.ClubDto;
import com.practice.demo.models.Club;

public interface ClubService {
	List<ClubDto> findAllClubs();
	
	Club saveClub(ClubDto clubDto);

	ClubDto findClubById(long clubId);

	void updateClub(ClubDto clubDto);

	void delete(long clubId);
	
	List<ClubDto> searchClubs(String query);
}
