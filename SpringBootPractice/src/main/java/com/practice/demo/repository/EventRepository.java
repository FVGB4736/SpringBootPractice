package com.practice.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.demo.models.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	

}
