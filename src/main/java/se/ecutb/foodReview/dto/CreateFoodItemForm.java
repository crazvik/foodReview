package se.ecutb.foodReview.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateFoodItemForm {

    @NotBlank(message = "Food name is mandatory")
    @Size(min = 2, max = 255, message = "Food name need to have 2 or more letters")
    private String foodItemName;
    @NotBlank(message = "Description is mandatory")
    @Size(min = 2, max = 255, message = "Description need to have 2 or more letters")
    private String desc;

    private int restaurantId;

    public String getFoodItemName() {
        return foodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
