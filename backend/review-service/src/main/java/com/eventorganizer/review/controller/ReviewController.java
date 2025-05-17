package com.eventorganizer.review.controller;

import com.eventorganizer.review.model.Review;
import com.eventorganizer.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return new ResponseEntity<>(reviewService.createReview(review), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.updateReview(id, review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<Review>> getReviewsByServiceId(@PathVariable Long serviceId) {
        return ResponseEntity.ok(reviewService.getReviewsByServiceId(serviceId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUserId(userId));
    }

    @GetMapping("/provider/{providerId}/rating")
    public ResponseEntity<Double> getAverageRatingByProviderId(@PathVariable Long providerId) {
        Double rating = reviewService.getAverageRatingByProviderId(providerId);
        return rating != null ? ResponseEntity.ok(rating) : ResponseEntity.notFound().build();
    }

    @GetMapping("/service/{serviceId}/rating")
    public ResponseEntity<Double> getAverageRatingByServiceId(@PathVariable Long serviceId) {
        Double rating = reviewService.getAverageRatingByServiceId(serviceId);
        return rating != null ? ResponseEntity.ok(rating) : ResponseEntity.notFound().build();
    }
} 