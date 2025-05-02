package com.eventorganizer.review.repository;

import com.eventorganizer.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByServiceId(Long serviceId);
    List<Review> findByUserId(Long userId);
    
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.serviceId IN " +
          "(SELECT s.id FROM com.eventorganizer.review.model.ServiceReference s WHERE s.providerId = :providerId)")
    Double findAverageRatingByProviderId(@Param("providerId") Long providerId);
    
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.serviceId = :serviceId")
    Double findAverageRatingByServiceId(@Param("serviceId") Long serviceId);
} 