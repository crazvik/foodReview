package se.ecutb.foodReview.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "restaurants")
    private List<Reviewers> reviewers;

    @OneToMany(mappedBy = "restaurant")
    private List<FoodItem> foodItems;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Reviewers> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<Reviewers> reviewers) {
        this.reviewers = reviewers;
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
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(reviewers, that.reviewers) &&
                Objects.equals(foodItems, that.foodItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, reviewers, foodItems);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reviewers=" + reviewers +
                ", foodItems=" + foodItems +
                '}';
    }
}
