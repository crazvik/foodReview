package se.ecutb.foodReview.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ReviewerRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    @Column(unique = true)
    private String role;

    public ReviewerRole() {
    }

    public ReviewerRole(String role) {
        this.role = role;
    }

    public int getId() {
        return roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewerRole that = (ReviewerRole) o;
        return roleId == that.roleId &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, role);
    }

    @Override
    public String toString() {
        return "ReviewerRole{" +
                "id=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }
}
