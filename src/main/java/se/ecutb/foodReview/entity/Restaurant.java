package se.ecutb.foodReview.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int restaurantId;
    private String name;

    @OneToMany(mappedBy = "restaurant")
    private List<FoodItem> foodItems;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public int getId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return restaurantId == that.restaurantId &&
                Objects.equals(name, that.name) &&
                Objects.equals(foodItems, that.foodItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, name, foodItems);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", foodItems=" + foodItems +
                '}';
    }
}
