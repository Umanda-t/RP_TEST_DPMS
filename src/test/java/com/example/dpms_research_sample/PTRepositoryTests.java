package com.example.dpms_research_sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class PTRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;
    @Autowired
    private PTRepository ptrepo;

    @Test
    public void testCreatePT() {
        User user=entityManager.find(User.class,"anne");
        PT object = new PT();
        object.setUsername(user);
        object.setSystolic(99);
        object.setDiastolic(80);
        object.setPulse(60);
        object.setDate("2022-05-12");
        object.setTime("12.30");

        ptrepo.save(object);

    }
}
