package com.eventorganizer.user.controller;

import com.eventorganizer.user.model.UserProfile;
import com.eventorganizer.user.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
    private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable String username) {
        try {
            logger.info("Getting user profile for username: {}", username);
            UserProfile userProfile = userProfileService.getUserProfile(username);
            return ResponseEntity.ok(userProfile);
        } catch (RuntimeException e) {
            logger.error("Error getting user profile: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{username}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfile> updateUserProfile(
            @PathVariable String username,
            @RequestBody UserProfile userProfile) {
        try {
            logger.info("Updating user profile for username: {}", username);
            logger.debug("Update request body: {}", userProfile);
            UserProfile updatedProfile = userProfileService.updateUserProfile(username, userProfile);
            return ResponseEntity.ok(updatedProfile);
        } catch (RuntimeException e) {
            logger.error("Error updating user profile: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUserProfile(@RequestBody UserProfile userProfile) {
        try {
            logger.info("Creating new user profile with username: {}", userProfile.getUsername());
            logger.debug("Create request body: {}", userProfile);
            UserProfile createdProfile = userProfileService.createUserProfile(userProfile);
            return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating user profile: {}", e.getMessage(), e);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable String username) {
        try {
            logger.info("Deleting user profile for username: {}", username);
            userProfileService.deleteUserProfile(username);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            logger.error("Error deleting user profile: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
} 