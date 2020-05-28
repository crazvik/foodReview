package se.ecutb.foodReview.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.ecutb.foodReview.entity.Reviewer;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReviewerRepoTest {
    @Autowired
    private ReviewerRepo reviewerRepo;

    @Test
    void findByUsernameIgnoreCase() {
        reviewerRepo.save(new Reviewer("Test", "Testson", "UserTest", "TestPassword", LocalDate.now()));
        reviewerRepo.save(new Reviewer("SecondTest", "SecondTestson", "SecondUserTest", "SecondTestPassword", LocalDate.now()));
        boolean exception = false;
        try {
            reviewerRepo.findByUsernameIgnoreCase("ExceptionTest").get().getUsername();
        } catch (NoSuchElementException e) {
            exception = true;
        }
        if (exception) {
            assertEquals("Test", reviewerRepo.findByUsernameIgnoreCase("UserTest").get().getFirstName());
        }
    }
}