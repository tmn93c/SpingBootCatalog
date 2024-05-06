package com.example.demo.startup;

import com.example.demo.model.TestEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TestService;
import com.example.demo.util.RsaKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.security.KeyPair;

@Component
@RequiredArgsConstructor
public class RunAfterStartup {

    @PersistenceContext
    private EntityManager entityManager;

    final UserRepository userRepository;

    final RoleRepository roleRepository;

    final TestService testService;

    @Autowired
    ResourceLoader resourceLoader;

    @Value("classpath:rsa.pub")
    Resource resourcePub;

    @Value("classpath:rsa.ppk")
    Resource resourcePrivate;


    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void insertWithQuery() throws Exception {
        KeyPair keyPair = RsaKey.generateKey();
        String message = "the answer to life the universe and everything";

        String encode = RsaKey.encrypt(message, keyPair.getPublic());
        String decode = RsaKey.decrypt(encode, keyPair.getPrivate());
        File file = resourcePub.getFile();

        String content = new String(Files.readAllBytes(file.toPath()));
//        PublicKey publicKey = RsaKey.getPubKey(file.toPath());
        TestEntity userModel = testService.getOneTest(1);

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
