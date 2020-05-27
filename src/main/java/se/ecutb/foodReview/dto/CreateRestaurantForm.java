package se.ecutb.foodReview.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateRestaurantForm {

    @NotBlank(message = "Restaurant name is mandatory")
    @Size(min = 2, max = 255, message = "Restaurant name need to have 2 or more letters")
    private String restaurantName;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

}
