package se.ecutb.foodReview.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.ecutb.foodReview.entity.Restaurant;
import se.ecutb.foodReview.entity.Reviewer;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RestaurantRepoTest {

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Test
    void findByNameIgnoreCase() {
        restaurantRepo.save(new Restaurant("TestRestaurant1"));
        restaurantRepo.save(new Restaurant("TestRestaurant2"));
        boolean exception = false;
        try {
            restaurantRepo.findByNameIgnoreCase("UserTest").get().getName();
        } catch (NoSuchElementException e) {
            exception = true;
        }
        if (exception) {
            assertEquals("TestRestaurant1", restaurantRepo.findByNameIgnoreCase("TestRestaurant1").get().getName());
        }
    }
}