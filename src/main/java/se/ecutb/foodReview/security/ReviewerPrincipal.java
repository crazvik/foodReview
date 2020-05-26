package se.ecutb.foodReview.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import se.ecutb.foodReview.entity.Reviewer;

import java.util.Collection;

public class ReviewerPrincipal implements UserDetails {
    private Reviewer reviewer;
    private Collection<GrantedAuthority> authorities;

    public ReviewerPrincipal(Reviewer reviewer, Collection<GrantedAuthority> authorities) {
        this.reviewer = reviewer;
        this.authorities = authorities;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return reviewer.getUsername();
    }

    @Override
    public String getPassword() {
        return reviewer.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
