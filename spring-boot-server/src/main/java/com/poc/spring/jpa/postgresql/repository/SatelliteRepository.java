package com.poc.spring.jpa.postgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.spring.jpa.postgresql.model.Satellites;

/*
 * This is repo for satellite 
 */
public interface SatelliteRepository extends JpaRepository<Satellites, Long> { 
  List<Satellites> findByLaunched(boolean launched);

  List<Satellites> findByCategory(String category);
}
