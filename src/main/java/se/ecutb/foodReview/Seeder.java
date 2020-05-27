package se.ecutb.foodReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.ecutb.foodReview.data.ReviewerRoleRepo;
import se.ecutb.foodReview.entity.ReviewerRole;
import se.ecutb.foodReview.service.RestaurantService;
import se.ecutb.foodReview.service.ReviewerService;

import java.time.LocalDate;

@Component
public class Seeder implements CommandLineRunner {
    private ReviewerService reviewerService;
    private ReviewerRoleRepo roleRepo;
    private RestaurantService restaurantService;

    @Autowired
    public Seeder(ReviewerService reviewerService, ReviewerRoleRepo roleRepo, RestaurantService restaurantService) {
        this.reviewerService = reviewerService;
        this.roleRepo = roleRepo;
        this.restaurantService = restaurantService;
    }

    @Override
    public void run(String... args) throws Exception {
        roleRepo.save(new ReviewerRole("USER"));
        roleRepo.save(new ReviewerRole("ADMIN"));

        reviewerService.registerReviewer("Admin", "Adminson", "Admin", "admin", LocalDate.now(), true);
        reviewerService.registerReviewer("User", "Userson", "User", "user", LocalDate.now(), false);

        restaurantService.registerRestaurant("First");
        restaurantService.registerRestaurant("Second");
    }
}
