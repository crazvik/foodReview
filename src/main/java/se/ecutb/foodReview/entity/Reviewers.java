package se.ecutb.foodReview.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Reviewers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String username;
    private String password;
    private LocalDate registrationDate;

    @ManyToMany(
            cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "user_reviews",
            joinColumns = @JoinColumn(name = "reviewer_Id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    )
    private List<Restaurant> restaurants;

    @ManyToMany
    @JoinTable(
            name = "user_reviews",
            joinColumns = @JoinColumn(name = "reviewer_Id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fooditem_Id", referencedColumnName = "id")
    )
    private List<FoodItem> foodItems;

    public Reviewers() {
    }

    public Reviewers(String firstName, String lastName, String username, String password, LocalDate registrationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
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
        Reviewers reviewers = (Reviewers) o;
        return id == reviewers.id &&
                Objects.equals(firstName, reviewers.firstName) &&
                Objects.equals(lastName, reviewers.lastName) &&
                Objects.equals(username, reviewers.username) &&
                Objects.equals(password, reviewers.password) &&
                Objects.equals(registrationDate, reviewers.registrationDate) &&
                Objects.equals(restaurants, reviewers.restaurants) &&
                Objects.equals(foodItems, reviewers.foodItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, username, password, registrationDate, restaurants, foodItems);
    }

    @Override
    public String toString() {
        return "Reviewers{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", registrationDate=" + registrationDate +
                ", restaurants=" + restaurants +
                ", foodItems=" + foodItems +
                '}';
    }
}
