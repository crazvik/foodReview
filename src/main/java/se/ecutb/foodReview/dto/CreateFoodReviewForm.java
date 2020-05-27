package se.ecutb.foodReview.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateFoodReviewForm {
    @NotBlank(message = "Food name is mandatory")
    @Size(min = 2, max = 255, message = "Food name need to have 2 or more letters")
    private String foodItemName;
    @NotBlank
    @Size(min = 2, max = 255, message = "Review need to have 2 or more letters")
    private String review;


    private String stars;

    public String getFoodItemName() {
        return foodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }
}
