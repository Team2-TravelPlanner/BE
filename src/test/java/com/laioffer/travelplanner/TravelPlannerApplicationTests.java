package com.laioffer.travelplanner;

import com.laioffer.travelplanner.entities.User;
import com.laioffer.travelplanner.repositories.UserRepository;
import com.laioffer.travelplanner.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TravelPlannerApplicationTests {

//	@Autowired
//	UserService userService;
//
//	@Autowired
//	PlanRepository repository;
//
//
//	@Test
//	void contextLoads() {
//	}
//
//	@Test
//	void insertUser() {
////		User user = new User();
////		user.setEmail("test1@gmail.com");
////		user.setPassword("123456");
////		user.setUsername("test");
////
////
////
////		Plan plan = new Plan();
////		plan.setPlanId(1);
////
////		Plan plan1 = new Plan();
////		plan.setPlanId(2);
////
////		repository.save(plan);
////		repository.save(plan1);
////
////		List<Plan> list = new ArrayList<>();
////		list.add(plan);
////		list.add(plan1);
////
////		user.setPlans(list);
//
//
//		//userService.saveUser(user);
//	}


}
