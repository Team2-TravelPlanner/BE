package com.laioffer.travelplanner;

import com.laioffer.travelplanner.entities.User;
import com.laioffer.travelplanner.repositories.UserRepository;
import com.laioffer.travelplanner.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootTest
class TravelPlannerApplicationTests {

	@Autowired
	UserService userService;


	@Test
	void contextLoads() {
	}

	@Test
	void insertUser() {
		User user = new User();
		user.setEmail("test@gmail.com");
		user.setPassword("123456");
		user.setUsername("test");

		userService.saveUser(user);
	}

}
