package com.eventorganizer.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/users")
public class UserServiceController {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceController.class);

    private final RestTemplate restTemplate;
    private final String userServiceUrl;

    public UserServiceController(RestTemplate restTemplate, 
                                 @Value("${app.service-urls.user-service}") String userServiceUrl) {
        this.restTemplate = restTemplate;
        this.userServiceUrl = userServiceUrl;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody String body) {
        logger.info("Forwarding user creation request to User Service");
        logger.debug("Request body: {}", body);
        
        String url = userServiceUrl + "/api/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        
        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                url, HttpMethod.POST, request, Object.class);
            
            logger.info("User Service responded with status: {}", response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("Error communicating with User Service: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error processing request: " + e.getMessage());
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        logger.info("Forwarding get user request to User Service for username: {}", username);
        
        String url = userServiceUrl + "/api/users/" + username;
        
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
            return response;
        } catch (Exception e) {
            logger.error("Error communicating with User Service: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error processing request: " + e.getMessage());
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody String body) {
        logger.info("Forwarding update user request to User Service for username: {}", username);
        
        String url = userServiceUrl + "/api/users/" + username;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        
        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                url, HttpMethod.PUT, request, Object.class);
            
            return response;
        } catch (Exception e) {
            logger.error("Error communicating with User Service: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error processing request: " + e.getMessage());
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        logger.info("Forwarding delete user request to User Service for username: {}", username);
        
        String url = userServiceUrl + "/api/users/" + username;
        
        try {
            restTemplate.delete(url);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error communicating with User Service: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error processing request: " + e.getMessage());
        }
    }
} 