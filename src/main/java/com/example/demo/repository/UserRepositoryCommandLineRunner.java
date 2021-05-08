package com.example.demo.repository;

import java.util.Collections;
import java.util.List;

import com.example.demo.model.RoleModel;
import com.example.demo.model.RoleName;
import com.example.demo.model.UserModel;
import com.example.demo.util.SearchCriteria;
import com.example.demo.util.AppConstants.SearchOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void run(String... args) {
		try {
			UserModel user_model = new UserModel("Tam","tamnd","tamnd@gmail.com","{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i");
            RoleModel userRole = roleRepository.findByName(RoleName.ROLE_USER.name()).get();
            user_model.setRoles(Collections.singleton(userRole));
            userRepository.save(user_model);

            UserModel user_model1 = new UserModel("Tu","tumnd","tumnd@gmail.com","{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i");
            user_model1.setRoles(Collections.singleton(userRole));
            userRepository.save(user_model1);

            UserModel user_model2 = new UserModel("Tin","timnd","timnd@gmail.com","{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i");
            user_model2.setRoles(Collections.singleton(userRole));
            userRepository.save(user_model2);

            UserModel user_model3 = new UserModel("tung","tungnd","tungnd@gmail.com","{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i");
            user_model3.setRoles(Collections.singleton(userRole));
            userRepository.save(user_model);
		log.info("-------------------------------");
		log.info("Finding all users");
		log.info("-------------------------------");
		for (UserModel user : userRepository.findAll()) {
			log.info(user.toString());
		}

		
	} catch (Exception ex) {
		log.error(ex.toString());
		System.out.println(ex);
	}
	}
	
	@Bean
    public CommandLineRunner specificationsDemo(UserSpeRepository userSpeRepository) {
        return args -> {


            // search movies by release year < 2010 and rating > 8
            UserSpecification msYearRating = new UserSpecification();
            msYearRating.add(new SearchCriteria("username", "t", SearchOperation.MATCH));
            List<UserModel> msYearRatingList = userSpeRepository.findAll(msYearRating);
            msYearRatingList.forEach(System.out::println);

        };
    }
}