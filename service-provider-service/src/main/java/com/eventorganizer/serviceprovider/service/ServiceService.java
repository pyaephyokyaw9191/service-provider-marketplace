package com.eventorganizer.serviceprovider.service;

import com.eventorganizer.serviceprovider.model.Service;
import com.eventorganizer.serviceprovider.repository.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;

    @Transactional
    public Service createService(Service service) {
        return serviceRepository.save(service);
    }

    @Transactional
    public Service updateService(Long id, Service updatedService) {
        Service existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + id));
        
        existingService.setTitle(updatedService.getTitle());
        existingService.setDescription(updatedService.getDescription());
        existingService.setKeySkills(updatedService.getKeySkills());
        existingService.setCategory(updatedService.getCategory());
        existingService.setHourlyRate(updatedService.getHourlyRate());
        existingService.setIsAvailable(updatedService.getIsAvailable());
        
        return serviceRepository.save(existingService);
    }

    @Transactional
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    public Service getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + id));
    }

    public List<Service> getServicesByProviderId(Long providerId) {
        return serviceRepository.findByProviderId(providerId);
    }

    public List<Service> getServicesByCategory(String category) {
        return serviceRepository.findByCategory(category);
    }

    public List<Service> getAvailableServices() {
        return serviceRepository.findByIsAvailableTrue();
    }

    public List<Service> getAvailableServicesByProviderId(Long providerId) {
        return serviceRepository.findByProviderIdAndIsAvailableTrue(providerId);
    }
} 