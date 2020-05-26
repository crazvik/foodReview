package se.ecutb.foodReview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.ecutb.foodReview.data.ReviewerRepo;
import se.ecutb.foodReview.data.ReviewerRoleRepo;
import se.ecutb.foodReview.entity.Reviewer;
import se.ecutb.foodReview.entity.ReviewerRole;
import se.ecutb.foodReview.security.ReviewerPrincipal;

import java.time.LocalDate;
import java.util.*;

@Service
public class ReviewerServiceImpl implements ReviewerService, UserDetailsService {
    private ReviewerRepo reviewerRepo;
    private ReviewerRoleRepo roleRepo;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ReviewerServiceImpl(ReviewerRepo reviewerRepo, ReviewerRoleRepo roleRepo, BCryptPasswordEncoder passwordEncoder) {
        this.reviewerRepo = reviewerRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Reviewer> reviewerOptional = reviewerRepo.findByUsernameIgnoreCase(username);
        Reviewer reviewer = reviewerOptional.orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));

        Collection<GrantedAuthority> authorities = new HashSet<>();
        for (ReviewerRole role : reviewer.getRoles()) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
            System.err.println(authority.getAuthority());
            authorities.add(authority);
        }
        return new ReviewerPrincipal(reviewer, authorities);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Reviewer registerReviewer(String firstName, String lastName, String username, String password, LocalDate regDate, boolean isAdmin) {
        Reviewer newReviewer = new Reviewer(
                firstName,
                lastName,
                username,
                passwordEncoder.encode(password),
                regDate);

        List<ReviewerRole> roles = new ArrayList<>();

        if (isAdmin) {
            ReviewerRole admin = roleRepo.findByRole("admin").orElseThrow(IllegalArgumentException::new);
            roles.add(admin);
        }

        ReviewerRole user = roleRepo.findByRole("user").orElseThrow(IllegalArgumentException::new);
        roles.add(user);

        newReviewer.setRoles(roles);
        return reviewerRepo.save(newReviewer);
    }
}
