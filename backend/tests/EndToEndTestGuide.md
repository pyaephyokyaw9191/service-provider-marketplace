## Service Provider Marketplace End-to-End Test Guide

This document outlines how to run the end-to-end tests for the Service Provider Marketplace application.

### Prerequisites

1. Java 17 or later installed
2. Maven 3.6 or later installed
3. IntelliJ IDEA (Ultimate Edition recommended, as Community Edition has limited HTTP Client capabilities)

### Starting the Required Services

Before running the tests, you need to start the following microservices:

1. User Service (port 8085)
2. Service Provider Service (port 8081)
3. Booking Service (port 8082)
4. Review Service (port 8083)
5. Auth Service (port 8084)

You can start them using the provided scripts:

#### On Windows:
```batch
run-all-services.bat
```

#### On Unix-based systems (Linux/macOS):
```bash
./run-all-services.sh
```

When the script runs successfully, you should see output confirming that all services are running on their respective ports.

### Running the End-to-End Test in IntelliJ IDEA

The end-to-end test is defined in `end-to-end-test.http` and can be executed using IntelliJ's HTTP Client. Follow these steps:

1. **Open the Project in IntelliJ IDEA**
   - Open IntelliJ IDEA
   - Select "Open" and navigate to the project folder
   - Choose the root directory and click "OK"

2. **Open the HTTP Request File**
   - Navigate to the `tests` folder in the Project view
   - Open the `end-to-end-test.http` file

3. **Running Individual Requests**
   - Inside the editor, you'll see "Run" icons (green play buttons) next to each request
   - Click the play button next to any request to execute it

4. **Running the Entire Test Sequence**
   - At the top of the file, you'll see a play button labeled "Run All Requests in File"
   - Click this button to execute all requests in sequence

5. **Using the Run HTTP Request Tool Window**
   - When you run a request, a tool window will open showing request/response details
   - The response will include status code, headers, and the response body
   - For requests that use variables, the variables will be populated from previous responses

### Understanding Variable Execution

The test file contains variables that are set from response data:

1. **Variable Definitions**: Variables are defined at the top of the file (e.g., `@userServiceUrl`)

2. **Response Capture**: Some requests include script blocks that capture data from responses:
   ```
   ### Create a service provider user
   # @name createServiceProvider
   POST {{userServiceUrl}}
   Content-Type: application/json
   
   {
     "username": "techexpert",
     ...
   }
   ```

3. **IntelliJ Environment Variables**: The HTTP client supports environment variables for different environments. If you'd like to use different environments:
   - Click the "HTTP Client Environment" dropdown in the top-right corner of the editor
   - Select "Edit HTTP Client Environment Variables"
   - Create environments for different testing scenarios (e.g., local, staging)

4. **Variable Persistence**: Variables are maintained throughout the test session, allowing later requests to reference IDs and values from earlier responses.

### Troubleshooting IntelliJ HTTP Client Issues

If you encounter issues:

1. **HTTP Client Plugin**: Make sure the HTTP Client plugin is enabled in IntelliJ (it's bundled with Ultimate Edition)
   - Go to File > Settings > Plugins
   - Search for "HTTP Client" and ensure it's enabled

2. **IntelliJ Version**: The HTTP Client works best in IntelliJ IDEA Ultimate. If you're using Community Edition, consider:
   - Upgrading to Ultimate
   - Using an alternative like Postman with the provided requests

3. **Variable Substitution Errors**: If you see "Invalid URI because of unsubstituted variable" errors:
   - Make sure you've run previous requests that set those variables
   - Consider using the updated version of the test file with hardcoded values

4. **Connection Issues**: If requests fail with connection errors:
   - Verify all services are running (check console output)
   - Confirm the ports in the URL variables match your running services

### Alternative Approaches in IntelliJ

1. **Creating a Run Configuration**:
   - Right-click on the .http file in the Project view
   - Select "Create 'Run All Requests in ...'"
   - This creates a permanent run configuration for the tests

2. **Using the HTTP Client Format in Java Files**:
   - IntelliJ also allows embedding HTTP requests in Java files as comments
   - This can be useful for documenting API endpoints alongside code

3. **Generate Code from Requests**:
   - Right-click on any request
   - Select "Convert Request to Java/JavaScript/Python/Go/C#"
   - This generates code to make the same request programmatically

### Viewing Request History

IntelliJ keeps a history of your HTTP requests:

1. Click the "HTTP Client" tool window button (usually at the bottom of the IDE)
2. View the "Request History" tab to see previous requests and responses
3. You can rerun any previous request from this history

### Test URLs

The test directly accesses each service:

- User Service: `http://localhost:8085/api/users`
- Service Provider Service: `http://localhost:8081/api/services`
- Booking Service: `http://localhost:8082/api/bookings`
- Review Service: `http://localhost:8083/api/reviews`
- Auth Service: `http://localhost:8084/api/auth`

## Interpreting Test Results

A successful test will:
1. Create all necessary entities
2. Establish relationships between them
3. Update entities and verify changes
4. Show the complete workflow from user registration to service completion and review

The final requests should show updated information reflecting all changes made throughout the test, confirming that the application's CRUD operations and business logic are working correctly.

## Notes for Development

For continuous integration, this test can be automated using tools like Newman (Postman CLI) or REST-assured with the same endpoints defined in the HTTP file. 