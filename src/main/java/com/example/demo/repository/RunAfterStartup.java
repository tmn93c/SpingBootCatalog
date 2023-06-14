package com.example.demo.repository;

import com.example.demo.mapper.TestMapper;
import com.example.demo.model.TestEntity;
import com.example.demo.model.UserModel;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Objects;

@Component
public class RunAfterStartup {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final TestMapper testMapper;


    public RunAfterStartup(UserRepository userRepository, RoleRepository roleRepository, TestMapper testMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.testMapper = testMapper;
    }

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void insertWithQuery() {
        Collection<TestEntity> testEntities = testMapper.findAll();
        TestEntity userModel = testMapper.getOneTest(1);

        for (int i = 0; i < 5000; i++) {
            try {
                String newUsername = "tamnd" + i;
                UserModel findUsername = userRepository.findByUsername(newUsername);
                if (Objects.isNull(findUsername)) {
                    entityManager.createNativeQuery(
                                    "INSERT INTO user_model (account_expired, account_locked, credentials_expired, email, enabled, name, password, username) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);")
                            .setParameter(1, false)
                            .setParameter(2, false)
                            .setParameter(3, false)
                            .setParameter(4, "tamnd@gmail.com" + i)
                            .setParameter(5, true)
                            .setParameter(6, "NGUYEN DINH TAM")
                            .setParameter(7, "{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i")
                            .setParameter(8, newUsername).executeUpdate();
                }
            } catch (NoResultException ex) {
                System.out.println(ex);
            }
        }
    }
}
