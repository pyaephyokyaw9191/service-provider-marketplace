#!/bin/bash

echo "Starting all microservices..."

# Set Java options for all services
JAVA_OPTS="-Xmx256m"

# Define service names and ports
SERVICE1="auth-service"
PORT1="8084"

SERVICE2="user-service"
PORT2="8085"

SERVICE3="service-provider-service"
PORT3="8081"

SERVICE4="booking-service"
PORT4="8082"

SERVICE5="review-service"
PORT5="8083"

# Function to start a service
start_service() {
    local service=$1
    local port=$2
    echo "Starting $service on port $port..."
    cd $service && mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=$port $JAVA_OPTS" &
    cd ..
    sleep 2
}

# Start each service
start_service $SERVICE1 $PORT1
start_service $SERVICE2 $PORT2
start_service $SERVICE3 $PORT3
start_service $SERVICE4 $PORT4
start_service $SERVICE5 $PORT5

echo "All services have been started."
echo
echo "Service status:"
echo "- $SERVICE1: http://localhost:$PORT1"
echo "- $SERVICE2: http://localhost:$PORT2"
echo "- $SERVICE3: http://localhost:$PORT3"
echo "- $SERVICE4: http://localhost:$PORT4"
echo "- $SERVICE5: http://localhost:$PORT5"
echo

# Store PIDs
PIDS=$(jobs -p)

# Clean up function
cleanup() {
    echo "Terminating all services..."
    for pid in $PIDS; do
        kill -9 $pid 2>/dev/null
    done
    echo "All services have been terminated."
    exit 0
}

# Set up trap to call cleanup function before script exits
trap cleanup SIGINT SIGTERM

echo "Press Ctrl+C to terminate all services..."
# Wait for user to press Ctrl+C
wait 