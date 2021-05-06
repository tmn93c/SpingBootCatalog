package com.example.demo.repository;

import java.util.Collections;

import com.example.demo.model.RoleModel;
import com.example.demo.model.RoleName;
import com.example.demo.model.UserModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
		user_model.setEnable(true);
        RoleModel userRole = roleRepository.findByName(RoleName.ROLE_ADMIN.name()).get();
        user_model.setRoles(Collections.singleton(userRole));
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

}