package com.example.demo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInsertTests {

    /**
     * Cách này sử dụng @SpringBootTest
     * Nó sẽ load toàn bộ Bean trong app của bạn lên,
     * Giống với việc chạy App.java vậy
     */

    /**
     * Đối tượng TodoRepository sẽ được mock, chứ không phải bean trong context
     */
    @PersistenceContext
    private EntityManager  entityManager;

    @Test
    public void testCount() {
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

}
