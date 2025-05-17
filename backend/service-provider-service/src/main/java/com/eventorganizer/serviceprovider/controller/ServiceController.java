package com.eventorganizer.serviceprovider.controller;

import com.eventorganizer.serviceprovider.model.Service;
import com.eventorganizer.serviceprovider.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    @PostMapping
    public ResponseEntity<Service> createService(@RequestBody Service service) {
        return new ResponseEntity<>(serviceService.createService(service), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> updateService(@PathVariable Long id, @RequestBody Service service) {
        return ResponseEntity.ok(serviceService.updateService(id, service));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.getServiceById(id));
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<Service>> getServicesByProviderId(@PathVariable Long providerId) {
        return ResponseEntity.ok(serviceService.getServicesByProviderId(providerId));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Service>> getServicesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(serviceService.getServicesByCategory(category));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Service>> getAvailableServices() {
        return ResponseEntity.ok(serviceService.getAvailableServices());
    }

    @GetMapping("/available/provider/{providerId}")
    public ResponseEntity<List<Service>> getAvailableServicesByProviderId(@PathVariable Long providerId) {
        return ResponseEntity.ok(serviceService.getAvailableServicesByProviderId(providerId));
    }
} 