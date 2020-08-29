package com.laioffer.travelplanner;

import com.laioffer.travelplanner.entities.Place;
import com.laioffer.travelplanner.entities.Plan;
import com.laioffer.travelplanner.entities.User;
import com.laioffer.travelplanner.repositories.PlaceRepository;
import com.laioffer.travelplanner.repositories.PlanRepository;
import com.laioffer.travelplanner.repositories.UserRepository;
import com.laioffer.travelplanner.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TravelPlannerApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	PlanRepository repository;

	@Autowired
	PlaceRepository placeRepository;


	@Test
	void contextLoads() {
	}

	@Test
	void insertUser() {
		User user = new User();
		user.setEmail("test1@gmail.com");
		user.setPassword("123456");
		user.setUsername("test");


		userService.saveUser(user);
	}

	@Test
	void fetchPlace() {
//		System.out.println(placeRepository.findByPlaceName("Empire State Building"));
//
//		System.out.println(placeRepository.findById("Wjy4JnQBkT9ktSN_PCmF"));
		placeRepository.deleteById("PnSUJ3QBGaLxU0MsmQ3i");
//		placeRepository.deleteById("kXRhJ3QBGaLxU0MsAwo1");
	}


}
