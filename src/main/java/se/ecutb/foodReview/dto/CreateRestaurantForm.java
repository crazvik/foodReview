package se.ecutb.foodReview.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateRestaurantForm {

    @NotBlank(message = "Restaurant name is mandatory")
    @Size(min = 2, max = 255, message = "Restaurant name need to have 2 or more letters")
    private String restaurantName;

    @NotBlank(message = "Review is mandatory")
    @Size(min = 2, max = 255, message = "Review need to have 2 or more letters")
    private String review;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
