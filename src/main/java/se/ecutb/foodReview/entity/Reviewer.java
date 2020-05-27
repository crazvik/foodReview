package se.ecutb.foodReview.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class Reviewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewerId;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String username;
    private String password;
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "reviewer")
    private List<Restaurant> restaurants;

    @ManyToMany(
            cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "reviewer_roles",
            joinColumns = @JoinColumn(name = "reviewer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<ReviewerRole> roles;

    public Reviewer() {
    }

    public Reviewer(String firstName, String lastName, String username, String password, LocalDate registrationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return reviewerId;
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

    public List<ReviewerRole> getRoles() {
        return roles;
    }

    public void setRoles(List<ReviewerRole> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reviewer reviewer = (Reviewer) o;
        return reviewerId == reviewer.reviewerId &&
                Objects.equals(firstName, reviewer.firstName) &&
                Objects.equals(lastName, reviewer.lastName) &&
                Objects.equals(username, reviewer.username) &&
                Objects.equals(password, reviewer.password) &&
                Objects.equals(registrationDate, reviewer.registrationDate) &&
                Objects.equals(restaurants, reviewer.restaurants) &&
                Objects.equals(roles, reviewer.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewerId, firstName, lastName, username, password, registrationDate, restaurants, roles);
    }

    @Override
    public String toString() {
        return "Reviewer{" +
                "reviewerId=" + reviewerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", registrationDate=" + registrationDate +
                ", restaurants=" + restaurants +
                ", roles=" + roles +
                '}';
    }
}
