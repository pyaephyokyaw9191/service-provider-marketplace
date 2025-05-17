package com.eventorganizer.booking.repository;

import com.eventorganizer.booking.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findByUserId(Long userId);
    List<ServiceRequest> findByServiceId(Long serviceId);
    List<ServiceRequest> findByServiceIdIn(List<Long> serviceIds);
    List<ServiceRequest> findByStatus(ServiceRequest.RequestStatus status);
} 