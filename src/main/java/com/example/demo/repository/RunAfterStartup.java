package com.example.demo.repository;

import java.io.Console;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.demo.model.RoleModel;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RunAfterStartup{

// @EventListener(ApplicationReadyEvent.class)
// public void runAfterStartup() {
//     System.out.println("Yaaah, I am running........");
// }
@PersistenceContext
private EntityManager  entityManager;

@Transactional
@EventListener(ApplicationReadyEvent.class)
public void insertWithQuery() {
    try {
        entityManager.createNativeQuery("INSERT INTO user_model (account_expired, account_locked, credentials_expired, email, enabled, name, password, username) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);")
        .setParameter(1, 0)
        .setParameter(2, 0)
        .setParameter(3, 0)
        .setParameter(4,  "tamnd@gmail.com")
        .setParameter(5, 1)
        .setParameter(6, "NGUYEN DINH TAM")
        .setParameter(7, "{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i")
        .setParameter(8, "tamnd")
        .executeUpdate();
    } catch (Exception ex) {
        System.out.println(ex);
    }
    
}

// @Transactional
// @EventListener(ApplicationReadyEvent.class)
// public void insertWithQuery() {
//     try {
//         List<RoleModel> l = entityManager.createNativeQuery("select * from role_model where id=?")
//         .setParameter(1, 1)
//         .getResultList();
//         System.out.println(l);
//     } catch (Exception ex) {
//         System.out.println(ex);
//     }
    
// }
}



