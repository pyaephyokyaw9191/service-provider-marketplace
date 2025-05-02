package com.eventorganizer.serviceprovider.repository;

import com.eventorganizer.serviceprovider.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findByProviderId(Long providerId);
    List<Service> findByCategory(String category);
    List<Service> findByIsAvailableTrue();
    List<Service> findByProviderIdAndIsAvailableTrue(Long providerId);
} 