package se.ecutb.foodReview.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String name;
    private String description;
    private String review;
    private int stars;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public FoodItem() {
    }

    public FoodItem(String name, String description, String review, int stars) {
        this.name = name;
        this.description = description;
        this.review = review;
        this.stars = stars;
    }

    public FoodItem(String name, String description, String review, int stars, Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.review = review;
        this.stars = stars;
        this.restaurant = restaurant;
    }

    public int getId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodItem foodItem = (FoodItem) o;
        return itemId == foodItem.itemId &&
                stars == foodItem.stars &&
                Objects.equals(name, foodItem.name) &&
                Objects.equals(description, foodItem.description) &&
                Objects.equals(review, foodItem.review) &&
                Objects.equals(restaurant, foodItem.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, name, description, review, stars, restaurant);
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", review='" + review + '\'' +
                ", stars=" + stars +
                ", restaurant=" + restaurant +
                '}';
    }
}
