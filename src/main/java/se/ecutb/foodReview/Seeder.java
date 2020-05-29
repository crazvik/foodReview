package se.ecutb.foodReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.ecutb.foodReview.data.ReviewerRoleRepo;
import se.ecutb.foodReview.entity.ReviewerRole;
import se.ecutb.foodReview.service.FoodItemService;
import se.ecutb.foodReview.service.RestaurantService;
import se.ecutb.foodReview.service.ReviewerService;

import java.time.LocalDate;

@Component
public class Seeder implements CommandLineRunner {
    private ReviewerService reviewerService;
    private ReviewerRoleRepo roleRepo;
    private RestaurantService restaurantService;
    private FoodItemService foodItemService;

    @Autowired
    public Seeder(ReviewerService reviewerService, ReviewerRoleRepo roleRepo, RestaurantService restaurantService, FoodItemService foodItemService) {
        this.reviewerService = reviewerService;
        this.roleRepo = roleRepo;
        this.restaurantService = restaurantService;
        this.foodItemService = foodItemService;
    }

    @Override
    public void run(String... args) throws Exception {
        roleRepo.save(new ReviewerRole("USER"));
        roleRepo.save(new ReviewerRole("ADMIN"));

        reviewerService.registerReviewer("Admin", "Adminson", "Admin", "admin", LocalDate.now(), true);
        reviewerService.registerReviewer("User", "Userson", "User", "user", LocalDate.now(), false);

        restaurantService.registerRestaurant("FirstRestaurant");
        restaurantService.registerRestaurant("SecondRestaurant");

        foodItemService.register("ThisFoodItem", "Added food with seeder to test", "", "", 1);
        foodItemService.register("ThatFoodItem", "Added food with seeder to test", "", "", 2);
        foodItemService.register("AnotherFoodItem", "Added more food with seeder to test", "", "", 2);
    }
}