package com.example.demo.repository;

import com.example.demo.mapper.TestMapper;
import com.example.demo.model.TestEntity;
import com.example.demo.model.UserModel;
import com.example.demo.util.RsaKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Collection;
import java.util.Objects;

@Component
public class RunAfterStartup {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final TestMapper testMapper;

    @Autowired
    ResourceLoader resourceLoader;

    @Value("classpath:rsa.pub")
    Resource resourcePub;

    @Value("classpath:rsa.ppk")
    Resource resourcePrivate;


    public RunAfterStartup(UserRepository userRepository, RoleRepository roleRepository, TestMapper testMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.testMapper = testMapper;
    }

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void insertWithQuery() throws Exception {
        KeyPair keyPair = RsaKey.generateKey();
        String message = "the answer to life the universe and everything";

        String encode = RsaKey.encrypt(message, keyPair.getPublic());
        String decode = RsaKey.decrypt(encode, keyPair.getPrivate());
        File file = resourcePub.getFile();

        String content = new String(Files.readAllBytes(file.toPath()));
        PublicKey publicKey = RsaKey.getPubKey(file.toPath());
        Collection<TestEntity> testEntities = testMapper.findAll();
        TestEntity userModel = testMapper.getOneTest(1);

//        for (int i = 0; i < 5000; i++) {
//            try {
//                String newUsername = "tamnd" + i;
//                UserModel findUsername = userRepository.findByUsername(newUsername);
//                if (Objects.isNull(findUsername)) {
//                    entityManager.createNativeQuery(
//                                    "INSERT INTO user_model (account_expired, account_locked, credentials_expired, email, enabled, name, password, username) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);")
//                            .setParameter(1, false)
//                            .setParameter(2, false)
//                            .setParameter(3, false)
//                            .setParameter(4, "tamnd@gmail.com" + i)
//                            .setParameter(5, true)
//                            .setParameter(6, "NGUYEN DINH TAM")
//                            .setParameter(7, "{bcrypt}$2a$10$iyZmEzd13MQGF46fnbUzO.ItgMPo2NoVCnVBvYPWIbqSh2WRI4W5i")
//                            .setParameter(8, newUsername).executeUpdate();
//                }
//            } catch (NoResultException ex) {
//                System.out.println(ex);
//            }
//        }
    }
}
