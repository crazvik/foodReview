package se.ecutb.foodReview.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String review;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToMany(mappedBy = "foodItems")
    private List<Reviewers> reviewers;

    public FoodItem() {
    }

    public FoodItem(String name, String description, String review) {
        this.name = name;
        this.description = description;
        this.review = review;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Reviewers> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<Reviewers> reviewers) {
        this.reviewers = reviewers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodItem foodItem = (FoodItem) o;
        return id == foodItem.id &&
                Objects.equals(name, foodItem.name) &&
                Objects.equals(description, foodItem.description) &&
                Objects.equals(review, foodItem.review) &&
                Objects.equals(restaurant, foodItem.restaurant) &&
                Objects.equals(reviewers, foodItem.reviewers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, review, restaurant, reviewers);
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", review='" + review + '\'' +
                ", restaurant=" + restaurant +
                ", reviewers=" + reviewers +
                '}';
    }
}
