package com.eventorganizer.review.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "service_references")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceReference {
    @Id
    private Long id;

    @Column(name = "provider_id", nullable = false)
    private Long providerId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;
} 