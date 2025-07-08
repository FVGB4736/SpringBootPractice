package com.practice.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practice.demo.models.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

	//Spring Data JPA 的命名規則（Query Method），Spring 會根據定義的方法名稱自動實作區此方法，自動產生對應的 SQL 查詢邏輯
	Optional<Club> findByTitle(String url);
	
	//自行指定SQL的查詢
	@Query("SELECT c FROM Club c WHERE c.title LIKE CONCAT('%', :query, '%')")
	List<Club> searchClubs(String query);
}
