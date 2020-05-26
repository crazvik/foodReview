package se.ecutb.foodReview.service;

import se.ecutb.foodReview.entity.Reviewer;

import java.time.LocalDate;

public interface ReviewerService {
    Reviewer registerReviewer(String firstName, String lastName, String username, String password, LocalDate regDate, boolean isAdmin);
}
