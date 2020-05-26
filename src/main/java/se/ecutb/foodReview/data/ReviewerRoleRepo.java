package se.ecutb.foodReview.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.ecutb.foodReview.entity.ReviewerRole;

import java.util.Optional;

public interface ReviewerRoleRepo extends CrudRepository<ReviewerRole, Integer> {
    @Query("SELECT r FROM ReviewerRole r WHERE UPPER(r.role) = UPPER(:role)")
    Optional<ReviewerRole> findByRole(@Param("role") String role);
}
