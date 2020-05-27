package se.ecutb.foodReview.data;

import org.springframework.data.repository.CrudRepository;
import se.ecutb.foodReview.entity.FoodItem;

import java.util.Optional;

public interface FoodItemRepo extends CrudRepository<FoodItem, Integer> {
    Optional<FoodItem> findByNameIgnoreCase(String name);
}
