package com.eventorganizer.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiGatewayController {
    private static final Logger logger = LoggerFactory.getLogger(ApiGatewayController.class);

    @Value("${app.service-urls.auth-service}")
    private String authServiceUrl;

    @Value("${app.service-urls.user-service}")
    private String userServiceUrl;

    @Value("${app.service-urls.service-provider-service}")
    private String serviceProviderServiceUrl;

    @Value("${app.service-urls.booking-service}")
    private String bookingServiceUrl;

    @Value("${app.service-urls.review-service}")
    private String reviewServiceUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public ApiGatewayController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/api/**")
    public ResponseEntity<Object> routeRequest(
            @RequestBody(required = false) String body,
            @RequestHeader HttpHeaders headers,
            HttpMethod method,
            HttpServletRequest request) throws URISyntaxException {

        String path = request.getRequestURI();
        logger.info("Received request: {} {}", method, path);
        logger.debug("Request body: {}", body);
        logger.debug("Request headers: {}", headers);
        
        String targetUrl;
        if (path.startsWith("/api/auth")) {
            targetUrl = authServiceUrl + path.substring("/api/auth".length());
        } else if (path.startsWith("/api/users")) {
            targetUrl = userServiceUrl + path.substring("/api/users".length());
        } else if (path.startsWith("/api/services")) {
            targetUrl = serviceProviderServiceUrl + path.substring("/api/services".length());
        } else if (path.startsWith("/api/bookings")) {
            targetUrl = bookingServiceUrl + path.substring("/api/bookings".length());
        } else if (path.startsWith("/api/reviews")) {
            targetUrl = reviewServiceUrl + path.substring("/api/reviews".length());
        } else {
            logger.warn("No route found for path: {}", path);
            return ResponseEntity.notFound().build();
        }

        // Add query string if present
        String queryString = request.getQueryString();
        if (queryString != null) {
            targetUrl += "?" + queryString;
        }

        logger.info("Routing to: {} {}", method, targetUrl);
        
        try {
            // Forward the request to the target service
            URI uri = new URI(targetUrl);
            HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
            ResponseEntity<Object> response = restTemplate.exchange(uri, method, httpEntity, Object.class);
            logger.info("Response status: {}", response.getStatusCode());
            return response;
        } catch (HttpStatusCodeException e) {
            logger.error("Service returned error: {} - {}", e.getStatusCode(), e.getResponseBodyAsString(), e);
            return ResponseEntity
                    .status(e.getStatusCode())
                    .headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        } catch (Exception e) {
            logger.error("Error routing request to {}: {}", targetUrl, e.getMessage(), e);
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("timestamp", java.time.LocalDateTime.now().toString());
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("error", "Gateway Error");
            errorResponse.put("message", e.getMessage());
            errorResponse.put("path", path);
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        logger.error("Unhandled exception: ", ex);
        
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", java.time.LocalDateTime.now().toString());
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.put("error", "Gateway Error");
        errorResponse.put("message", ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
} 