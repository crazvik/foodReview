package se.ecutb.foodReview.data;

import org.springframework.data.repository.CrudRepository;
import se.ecutb.foodReview.entity.Reviewer;

import java.util.Optional;

public interface ReviewerRepo extends CrudRepository<Reviewer, Integer> {
    Optional<Reviewer> findByUsernameIgnoreCase(String username);


}
