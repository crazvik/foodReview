package se.ecutb.foodReview.data;

import org.springframework.data.repository.CrudRepository;
import se.ecutb.foodReview.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepo extends CrudRepository<Restaurant, Integer> {
    Optional<Restaurant> findByNameIgnoreCase(String name);
}
