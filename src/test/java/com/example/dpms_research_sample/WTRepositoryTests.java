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
public class WTRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;
    @Autowired
    private WTRepository wtrepo;

    @Test
    public void testCreateWT() {
        User user=entityManager.find(User.class,"anne");
        WT object = new WT();
        object.setUsername(user);
        object.setWeight(55);
        object.setHeight(1.65);
        object.setDate("2022-06-07");
        wtrepo.save(object);

    }

}
