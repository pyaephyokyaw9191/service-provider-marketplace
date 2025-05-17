package com.eventorganizer.review.service;

import com.eventorganizer.review.model.Review;
import com.eventorganizer.review.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    @Transactional
    public Review updateReview(Long id, Review updatedReview) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + id));
        
        existingReview.setRating(updatedReview.getRating());
        existingReview.setComment(updatedReview.getComment());
        
        return reviewRepository.save(existingReview);
    }

    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + id));
    }

    public List<Review> getReviewsByServiceId(Long serviceId) {
        return reviewRepository.findByServiceId(serviceId);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    public Double getAverageRatingByProviderId(Long providerId) {
        return reviewRepository.findAverageRatingByProviderId(providerId);
    }

    public Double getAverageRatingByServiceId(Long serviceId) {
        return reviewRepository.findAverageRatingByServiceId(serviceId);
    }
} 