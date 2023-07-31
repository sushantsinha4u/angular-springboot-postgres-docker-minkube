package com.poc.spring.jpa.postgresql;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.spring.jpa.postgresql.model.Satellites;
import com.poc.spring.jpa.postgresql.service.SatelliteService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class SpringBootJpaPostgresqlApplicationTests {

	@Autowired
	private SatelliteService satelliteService;


	List<Satellites> satListJson =new ArrayList<>();
	List<Satellites> satList=new ArrayList<>();
	List<Satellites> satListTest = new ArrayList<>();


	@Before
	public  void setUp() throws IOException {

		final ObjectMapper objectMapper = new ObjectMapper();
		
		satListJson = objectMapper.readValue( new File("satelliteData.json"), new TypeReference<List<Satellites>>(){});
		
		this.satListTest.add(new Satellites("MEO", "Yoga-R15","Flexible to give support to defense system",true));
		this.satListTest.add(new Satellites("LEO", "O3b mPower Telenetworking & Networking","O3b mPower Telenetworking is the braodcasting satellite all over the world",false));
		this.satListTest.add(new Satellites("GEO", "SISU-X031","Covering the mediterranean and landscapes worlwide",true));
	}

	@Test
	public void getAllSatellitesTest() throws Exception {

		satList= satelliteService.getAllSatellitess(null);

		assertThat(satList).isNotEmpty();
		assertThat(satList).isNotNull();


		//check first  etails from Json Data 
		assertThat(satListJson.get(0).getName()).isEqualTo(this.satListTest.get(0).getName());
		assertThat(satListJson.get(0).getCategory()).isEqualTo(this.satListTest.get(0).getCategory());
		assertThat(satListJson.get(0).isLaunched()).isEqualTo(this.satListTest.get(0).isLaunched());


		//check second details Json Data 
		assertThat(satListJson.get(1).getName()).isEqualTo(this.satListTest.get(1).getName());
		assertThat(satListJson.get(1).getCategory()).isEqualTo(this.satListTest.get(1).getCategory());
		assertThat(satListJson.get(1).isLaunched()).isEqualTo(this.satListTest.get(1).isLaunched());


		//check third details Json Data 
		assertThat(satListJson.get(2).getName()).isEqualTo(this.satListTest.get(2).getName());
		assertThat(satListJson.get(2).getCategory()).isEqualTo(this.satListTest.get(2).getCategory());
		assertThat(satListJson.get(2).isLaunched()).isEqualTo(this.satListTest.get(2).isLaunched());
	}

	@Test
	public void getSatellitesByCategoryTest() throws Exception {
		String category = "LEO";

		satList= satelliteService.getAllSatellitess(category);

		List<Satellites> satListFilter= satListTest.stream()
				.filter(p -> p.getCategory().equals(category)).collect(Collectors.toList());

		assertThat(satList.get(0).getName()).isEqualTo(satListFilter.get(0).getName());
		assertThat(satList.get(0).getCategory()).isEqualTo(satListFilter.get(0).getCategory());
		assertThat(satList.get(0).isLaunched()).isEqualTo(satListFilter.get(0).isLaunched());
	}


	@Test
	public void createSatellites() {
		Satellites satellite;

		satellite = new Satellites("MEO", "Delta","Eliptical Satellite",false);

		Satellites satelliteTest = satelliteService.createSatellites(satellite);

		assertThat(satelliteTest.getName()).isEqualTo(satellite.getName());
	}



	@Test
	public void updateSatellite() {

		long id_update = 58;
		
		Satellites satelliteUpdate = new Satellites("MEO", "Omega","Superfast Satellite system",true);

		ResponseEntity<Satellites>  res =satelliteService.updateSatellites(id_update, satelliteUpdate);
		
		assertThat(res.getStatusCode().is2xxSuccessful()).isEqualTo(true);
	}

}
