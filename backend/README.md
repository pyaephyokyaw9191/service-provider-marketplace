# Service Provider Marketplace

A microservice-based platform that connects service providers with clients, facilitating service discovery, booking, and reviews.

## 🚀 Project Overview

This application demonstrates a modern microservice architecture for a marketplace platform where:
- Service providers can list their professional services
- Clients can discover, book, and review these services
- Both parties can manage their profiles and interactions

## 🏗️ Architecture

The platform is built using a microservice architecture with the following components:

### Microservices

- **User Service** (Port 8085): Manages user profiles for both service providers and clients
- **Service Provider Service** (Port 8081): Handles service listings, categories, and provider-specific operations
- **Booking Service** (Port 8082): Manages the booking workflow from request to completion
- **Review Service** (Port 8083): Handles reviews and ratings for completed services
- **Auth Service** (Port 8084): Provides authentication and authorization capabilities

Each microservice:
- Has its own database
- Exposes a RESTful API
- Can be developed, deployed, and scaled independently

## 🛠️ Technology Stack

- **Backend**: Java 17, Spring Boot, Spring Data JPA
- **Database**: H2 (in-memory for development)
- **Build Tool**: Maven
- **API Documentation**: SpringDoc OpenAPI

## 📋 Prerequisites

To run this application, you need:

- Java 17 or later
- Maven 3.6 or later
- Git

## 🚦 Getting Started

### Clone the Repository

```bash
git clone https://github.com/yourusername/service-provider-marketplace.git
cd service-provider-marketplace
```

### Build All Services

```bash
mvn clean install
```

### Run All Services

Use the provided script to start all microservices:

#### On Windows:
```batch
run-all-services.bat
```

#### On Unix-based systems (Linux/macOS):
```bash
./run-all-services.sh
```

When the script runs successfully, you should see output confirming that all services are running on their respective ports:

```
Starting all microservices...
Starting auth-service on port 8084...
Starting user-service on port 8085...
Starting service-provider-service on port 8081...
Starting booking-service on port 8082...
Starting review-service on port 8083...
All services have been started.

Service status:
- auth-service: http://localhost:8084
- user-service: http://localhost:8085
- service-provider-service: http://localhost:8081
- booking-service: http://localhost:8082
- review-service: http://localhost:8083
```

## 🧪 Testing the Application

### End-to-End Tests

The application includes comprehensive end-to-end tests that validate the complete workflow:

1. Navigate to the `tests` directory
2. Use the `end-to-end-test.http` file with IntelliJ's HTTP Client or a similar tool
3. Execute the requests to test the full workflow from user creation to service booking and reviews

For detailed instructions on running the tests, see the [End-to-End Test Guide](tests/EndToEndTestGuide.md).

### API Documentation

Each service exposes a Swagger UI endpoint for API exploration:

- User Service: http://localhost:8085/swagger-ui.html
- Service Provider Service: http://localhost:8081/swagger-ui.html
- Booking Service: http://localhost:8082/swagger-ui.html
- Review Service: http://localhost:8083/swagger-ui.html
- Auth Service: http://localhost:8084/swagger-ui.html

## 📚 Core Features

### User Management
- User registration for both providers and clients
- Profile creation and management
- User search and lookup

### Service Management
- Create and manage service listings
- Categorize services
- Search services by provider, category, availability, etc.

### Booking Workflow
- Request a booking for a service
- Accept/decline booking requests
- Track booking status
- Mark bookings as completed

### Review System
- Submit reviews for completed services
- Rate providers
- View aggregate ratings and feedback

### Authentication (in progress)
- Secure login
- JWT token-based authentication
- Role-based authorization

## 🔍 Implementation Details

### Communication Patterns

The microservices communicate using the following patterns:
- RESTful APIs for synchronous service-to-service communication
- Future plans include adding asynchronous communication via message queues

### Data Persistence

Each service has its own database:
- For development: H2 in-memory database
- For production: Can be configured to use PostgreSQL, MySQL, etc.

### Validation & Error Handling

- Input validation on all API endpoints
- Comprehensive error handling with appropriate HTTP status codes
- Detailed error messages for troubleshooting

## 🛣️ Roadmap

- [ ] Add authentication with JWT tokens
- [ ] Implement notification service for email and SMS alerts
- [ ] Add payment processing integration
- [ ] Enhance search with filters and sorting
- [ ] Implement service availability calendar

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Contact

For any questions or suggestions, please open an issue or contact the repository maintainers. 