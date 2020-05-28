package se.ecutb.foodReview.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.ecutb.foodReview.entity.FoodItem;
import se.ecutb.foodReview.entity.Restaurant;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FoodItemRepoTest {

    @Autowired
    private FoodItemRepo foodItemRepo;

    @Test
    void findByNameIgnoreCase() {
        foodItemRepo.save(new FoodItem("TestFood1", "Test description 1 here", "Test review here", "3"));
        foodItemRepo.save(new FoodItem("TestFood2", "Test description 2 here", "Test review here as well", "5"));
        boolean exception = false;
        try {
            foodItemRepo.findByNameIgnoreCase("UserTest").get().getName();
        } catch (NoSuchElementException e) {
            exception = true;
        }
        if (exception) {
            assertEquals("TestFood1", foodItemRepo.findByNameIgnoreCase("TestFood1").get().getName());
        }
    }
}