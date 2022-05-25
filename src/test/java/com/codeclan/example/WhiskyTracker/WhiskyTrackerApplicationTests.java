package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;
	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getAllWhiskiesByYear(){
		List<Whisky> whiskies = whiskyRepository.findByYear(2018);
		assertEquals(6, whiskies.size());
	}

	@Test
	public void getAllDistilleriesFromARegion(){
		List<Distillery> distilleries = distilleryRepository.findByRegion("Islay");
		assertEquals(2, distilleries.size());
	}

	@Test
	public void getAllWhiskiesFromDistilleryByYear(){
		Distillery distillery = distilleryRepository.findByName("Lagavulin");
		List<Whisky> whiskies= whiskyRepository.findByDistilleryAndAge(distillery, 16);
		assertEquals(1, whiskies.size());
	}

	@Test
	public void getAllWhiskiesFromARegion(){
		List<Whisky> whiskies = whiskyRepository.findByDistilleryRegion("Islay");
		assertEquals(2, whiskies.size());
	}

	@Test
	public void getDistilleriesWithWhiskiesOfSpecificAge(){
		List<Distillery> distilleries = distilleryRepository.findByWhiskiesAge(12);
		assertEquals(6, distilleries.size());
	}

}
