package com.example.demo.repository;

import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.demo.model.RoleModel;
import com.example.demo.model.RoleName;
import com.example.demo.model.UserModel;

import org.springframework.stereotype.Component;

@Component
public class RunAfterStartup {
    @PersistenceContext
    private EntityManager entityManager;

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    // @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void runAfterStartup() {
        System.out.println("Yaaah, I am running........");
        try {
            // entityManager.createNativeQuery(
            //         "INSERT INTO user_model (account_expired, account_locked, credentials_expired, email, enabled, name, password, username) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);")
            //         .setParameter(1, 0).setParameter(2, 0).setParameter(3, 0).setParameter(4, "tamnd@gmail.com")
            //         .setParameter(5, 1).setParameter(6, "NGUYEN DINH TAM")
            //         .setParameter(7, "{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i")
            //         .setParameter(8, "tamnd").executeUpdate();
            UserModel user_model = new UserModel("Tam","tamnd","tamnd@gmail.com","{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i",true);
            RoleModel userRole = roleRepository.findByName(RoleName.ROLE_USER.name()).get();
            user_model.setRoles(Collections.singleton(userRole));
            userRepository.save(user_model);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Transactional
    // @EventListener(ApplicationReadyEvent.class)
    public void insertWithQuery() {
        try {
            entityManager.createNativeQuery(
                    "INSERT INTO user_model (account_expired, account_locked, credentials_expired, email, enabled, name, password, username) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);")
                    .setParameter(1, 0).setParameter(2, 0).setParameter(3, 0).setParameter(4, "tamnd@gmail.com")
                    .setParameter(5, 1).setParameter(6, "NGUYEN DINH TAM")
                    .setParameter(7, "{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i")
                    .setParameter(8, "tamnd").executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    // @Transactional
    // @EventListener(ApplicationReadyEvent.class)
    // public void insertWithQuery() {
    // try {
    // List<RoleModel> l = entityManager.createNativeQuery("select * from role_model
    // where id=?")
    // .setParameter(1, 1)
    // .getResultList();
    // System.out.println(l);
    // } catch (Exception ex) {
    // System.out.println(ex);
    // }

    // }
}
