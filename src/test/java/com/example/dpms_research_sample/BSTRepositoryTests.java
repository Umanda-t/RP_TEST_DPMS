package com.example.dpms_research_sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class BSTRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;
    @Autowired
    private BSTRepository bstrepo;

    @Test
    public void testCreateBST() {
        User user=entityManager.find(User.class,"anne");
        BST object = new BST();
        object.setUsername(user);
        object.setBloodsugar(99);
        object.setDate("10/09/2022");
        object.setTime("12.30");
        object.setPeriod("After Lunch");

        bstrepo.save(object);

    }
}
