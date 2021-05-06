package com.example.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


public class UserInsertRepository  {
    @PersistenceContext
    private EntityManager  entityManager;
    
    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void insertWithQuery() {
        entityManager.createNativeQuery("INSERT INTO user_model (id, account_expired, account_locked, credentials_expired, email, enabled, name, password, username) VALUES (?, 0, 0, 0, '', ?, 1, '{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i', ?)")
        .setParameter(1, 1)
        .setParameter(9, "tamnd")
        .setParameter(5,  "tamnd@gmail.com")
        .executeUpdate();
    }
}
