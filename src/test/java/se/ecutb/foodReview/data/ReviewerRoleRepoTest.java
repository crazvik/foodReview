package se.ecutb.foodReview.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.ecutb.foodReview.entity.Reviewer;
import se.ecutb.foodReview.entity.ReviewerRole;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReviewerRoleRepoTest {

    @Autowired
    ReviewerRoleRepo roleRepo;

    @Test
    void findByRole() {
        roleRepo.save(new ReviewerRole("USER"));
        roleRepo.save(new ReviewerRole("ADMIN"));
        boolean exception = false;
        try {
            roleRepo.findByRole("ExceptionTest").get().getRole();
        } catch (NoSuchElementException e) {
            exception = true;
        }
        if (exception) {
            boolean isPresent = roleRepo.findByRole("user").isPresent();
            assertTrue(isPresent);
            String testRole = roleRepo.findByRole("admin").get().getRole();
            assertTrue("Admin".equalsIgnoreCase(testRole));
        }
    }
}