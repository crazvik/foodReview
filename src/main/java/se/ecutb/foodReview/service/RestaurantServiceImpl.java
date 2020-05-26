package se.ecutb.foodReview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.ecutb.foodReview.data.RestaurantRepo;
import se.ecutb.foodReview.entity.Restaurant;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepo restaurantRepo;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Restaurant registerRestaurant(String name) {
        Restaurant newRestaurant = new Restaurant(name);
        return restaurantRepo.save(newRestaurant);
    }
}
