package com.poc.spring.jpa.postgresql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.poc.spring.jpa.postgresql.model.Satellites;
import com.poc.spring.jpa.postgresql.repository.SatelliteRepository;


@Service
public class SatelliteService  {
	@Autowired
	SatelliteRepository satelliteRepository;

	public List<Satellites> getAllSatellitess(String category){
		List<Satellites> satellites = new ArrayList<Satellites>();
		if (category == null)
			satelliteRepository.findAll().forEach(satellites::add);
		else
			satelliteRepository.findByCategory(category).forEach(satellites::add);

		return satellites;
	}


	public ResponseEntity<Satellites>  updateSatellites(long id, Satellites satellite) {
		Optional<Satellites> satelliteData = satelliteRepository.findById(id);
		Satellites _satellite =null;
		if (satelliteData.isPresent()) {
			_satellite = satelliteData.get();
			_satellite.setCategory(satellite.getCategory());
			_satellite.setName(satellite.getName());
			_satellite.setDescription(satellite.getDescription());
			_satellite.setLaunched(satellite.isLaunched());
		}
		if (_satellite!=null) {	
			return new ResponseEntity<>(satelliteRepository.save(_satellite), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	public List<Satellites> findByLaunched(boolean launched) {
		List<Satellites> satellites = satelliteRepository.findByLaunched(launched);
		return satellites;			

	}

	public void deleteSatellites(long id) {
		satelliteRepository.deleteById(id);	
	}

	public void deleteAllSatellitess() {
		satelliteRepository.deleteAll();	
	}



	public Satellites createSatellites(Satellites satellite) {
		System.out.println(satellite);
		return satelliteRepository.save(new Satellites(satellite.getCategory(), 
				satellite.getName(),satellite.getDescription(), false));
	}


	public Optional<Satellites> getSatellitesById(long id) {
		return satelliteRepository.findById(id);

	}

}
