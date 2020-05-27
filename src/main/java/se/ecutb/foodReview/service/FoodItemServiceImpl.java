package se.ecutb.foodReview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.ecutb.foodReview.data.FoodItemRepo;
import se.ecutb.foodReview.data.RestaurantRepo;
import se.ecutb.foodReview.entity.FoodItem;

@Service
public class FoodItemServiceImpl implements FoodItemService {
    private FoodItemRepo foodItemRepo;
    private RestaurantRepo restaurantRepo;

    @Autowired
    public FoodItemServiceImpl(FoodItemRepo foodItemRepo, RestaurantRepo restaurantRepo) {
        this.foodItemRepo = foodItemRepo;
        this.restaurantRepo = restaurantRepo;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public FoodItem register(String name, String desc, String review, String stars, int restaurantId) {
        FoodItem newFoodItem = new FoodItem(name, desc, review, stars, restaurantRepo.findById(restaurantId).get());
        return foodItemRepo.save(newFoodItem);
    }
}
