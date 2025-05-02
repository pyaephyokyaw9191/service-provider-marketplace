package com.eventorganizer.booking.service;

import com.eventorganizer.booking.model.ServiceRequest;
import com.eventorganizer.booking.repository.ServiceRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceRequestService {
    private final ServiceRequestRepository serviceRequestRepository;

    @Transactional
    public ServiceRequest createServiceRequest(ServiceRequest request) {
        return serviceRequestRepository.save(request);
    }

    @Transactional
    public ServiceRequest updateRequestStatus(Long id, ServiceRequest.RequestStatus newStatus) {
        ServiceRequest request = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service request not found with id: " + id));
        
        request.setStatus(newStatus);
        return serviceRequestRepository.save(request);
    }

    public ServiceRequest getRequestById(Long id) {
        return serviceRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service request not found with id: " + id));
    }

    public List<ServiceRequest> getRequestsByUserId(Long userId) {
        return serviceRequestRepository.findByUserId(userId);
    }

    public List<ServiceRequest> getRequestsByServiceId(Long serviceId) {
        return serviceRequestRepository.findByServiceId(serviceId);
    }

    public List<ServiceRequest> getRequestsByServiceIds(List<Long> serviceIds) {
        return serviceRequestRepository.findByServiceIdIn(serviceIds);
    }

    public List<ServiceRequest> getRequestsByStatus(ServiceRequest.RequestStatus status) {
        return serviceRequestRepository.findByStatus(status);
    }
} 