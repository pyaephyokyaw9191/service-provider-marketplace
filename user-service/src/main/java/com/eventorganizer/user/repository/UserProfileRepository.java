package com.eventorganizer.user.repository;

import com.eventorganizer.user.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUsername(String username);
    Optional<UserProfile> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
} 