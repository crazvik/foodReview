package se.ecutb.foodReview.service;

import se.ecutb.foodReview.entity.FoodItem;

public interface FoodItemService {
    FoodItem register(String name, String desc, String review, int stars, int restaurantId);
}
