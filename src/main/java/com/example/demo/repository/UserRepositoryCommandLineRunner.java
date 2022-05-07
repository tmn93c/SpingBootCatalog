package com.example.demo.repository;

import com.example.demo.model.RoleModel;
import com.example.demo.model.RoleName;
import com.example.demo.model.UserModel;
import com.example.demo.model.studentRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) {
        try {
            Optional<RoleModel> oneCheck = roleRepository.findById((long) 1);
            if (oneCheck != null) {
                return;
            } else {
                List<RoleModel> list_role_model = new ArrayList<>();
                for (RoleName role : RoleName.values()) {
                    System.out.println(role);
                    RoleModel role_model = new RoleModel();
                    role_model.setName(role.toString());
                    role_model.setCode(role.toString());
                    list_role_model.add(role_model);
                }
                roleRepository.saveAll(list_role_model);
                List<UserModel> list_user_model = new ArrayList<>();
                UserModel user_model = new UserModel("Tam", "tamnd", "tamnd@gmail.com",
                        "{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i", true);
                RoleModel userRole = roleRepository.findByName(RoleName.ROLE_USER.name()).get();
                user_model.setRoles(Collections.singleton(userRole));
                list_user_model.add(user_model);

                UserModel user_model1 = new UserModel("Tu", "tumnd", "tumnd@gmail.com",
                        "{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i", true);
                user_model1.setRoles(Collections.singleton(userRole));
                list_user_model.add(user_model1);

                UserModel user_model2 = new UserModel("Tin", "timnd", "timnd@gmail.com",
                        "{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i", true);
                user_model2.setRoles(Collections.singleton(userRole));
                list_user_model.add(user_model2);

                UserModel user_model3 = new UserModel("tung", "tungnd", "tungnd@gmail.com",
                        "{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i", true);
                user_model3.setRoles(Collections.singleton(userRole));
                list_user_model.add(user_model3);

                userRepository.saveAll(list_user_model);

                log.info("-------------------------------");
                log.info("Finding all users");
                log.info("-------------------------------");
                for (UserModel user : userRepository.findAll()) {
                    log.info(user.toString());
                }

                List<studentRedis> list_student_model = new ArrayList<>();
                studentRedis student = new studentRedis("Eng2015001", "John Doe", studentRedis.Gender.MALE, 1);
                list_student_model.add(student);
                studentRedis student1 = new studentRedis("Eng2015002", "John Doe 2", studentRedis.Gender.MALE, 1);
                list_student_model.add(student1);
                studentRepository.saveAll(list_student_model);
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
            // UserSpecification msYearRating = new UserSpecification();
            // msYearRating.add(new SearchCriteria("username", "t", SearchOperation.MATCH));
            // List<UserModel> msYearRatingList = userSpeRepository.findAll(msYearRating);
            // msYearRatingList.forEach(System.out::println);

        };
    }
}