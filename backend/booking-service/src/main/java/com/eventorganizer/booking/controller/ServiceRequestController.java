package com.eventorganizer.booking.controller;

import com.eventorganizer.booking.model.ServiceRequest;
import com.eventorganizer.booking.service.ServiceRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    @PostMapping
    public ResponseEntity<ServiceRequest> createServiceRequest(@RequestBody ServiceRequest request) {
        return new ResponseEntity<>(serviceRequestService.createServiceRequest(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ServiceRequest> updateRequestStatus(
            @PathVariable Long id, 
            @RequestParam ServiceRequest.RequestStatus status) {
        return ResponseEntity.ok(serviceRequestService.updateRequestStatus(id, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequest> getRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceRequestService.getRequestById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ServiceRequest>> getRequestsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(serviceRequestService.getRequestsByUserId(userId));
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<ServiceRequest>> getRequestsByServiceId(@PathVariable Long serviceId) {
        return ResponseEntity.ok(serviceRequestService.getRequestsByServiceId(serviceId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ServiceRequest>> getRequestsByStatus(
            @PathVariable ServiceRequest.RequestStatus status) {
        return ResponseEntity.ok(serviceRequestService.getRequestsByStatus(status));
    }
} 