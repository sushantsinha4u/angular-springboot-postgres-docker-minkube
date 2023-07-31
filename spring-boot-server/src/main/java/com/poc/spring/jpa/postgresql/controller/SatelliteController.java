package com.poc.spring.jpa.postgresql.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.spring.jpa.postgresql.model.Satellites;
import com.poc.spring.jpa.postgresql.repository.SatelliteRepository;
import com.poc.spring.jpa.postgresql.service.SatelliteService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SatelliteController {

	@Autowired
	SatelliteRepository satelliteRepository;

	@Autowired
	SatelliteService satelliteService;

	@GetMapping("/satellites")
	public ResponseEntity<List<Satellites>> getAllSatellitess(@RequestParam(required = false) String category) {
		try {
			List<Satellites> satellites = satelliteService.getAllSatellitess(category);
			if (satellites.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(satellites, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/satellites/{id}")
	public ResponseEntity<Satellites> getSatellitesById(@PathVariable("id") long id) {
		Optional<Satellites> satelliteData = satelliteService.getSatellitesById(id);
		if (satelliteData.isPresent()) {
			return new ResponseEntity<>(satelliteData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@PostMapping("/satellites")
	public ResponseEntity<Satellites> createSatellites(@RequestBody final Satellites satellite) {
		try {
			return new ResponseEntity<>(satelliteService.createSatellites(satellite), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PutMapping("/satellites/{id}")
	public ResponseEntity<Satellites> updateSatellites(@PathVariable("id") long id, @RequestBody Satellites satellite) {
		return satelliteService.updateSatellites(id, satellite);

	}

	@DeleteMapping("/satellites/{id}")
	public ResponseEntity<HttpStatus> deleteSatellites(@PathVariable("id") long id) {
		try {
			satelliteService.deleteSatellites(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@DeleteMapping("/satellites")
	public ResponseEntity<HttpStatus> deleteAllSatellitess() {
		try {
			satelliteService.deleteAllSatellitess();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@GetMapping("/satellites/launched")
	public ResponseEntity<List<Satellites>> findByLaunched() {
		try {
			List<Satellites> satellites = satelliteService.findByLaunched(true);
			if (satellites.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(satellites, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
