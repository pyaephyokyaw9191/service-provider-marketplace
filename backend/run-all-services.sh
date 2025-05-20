#!/bin/bash

# Services and their directories (in the order you provided)
services=(
  "auth-service"
  "user-service"
  "service-provider-service"
  "booking-service"
  "review-service"
  "payment-service"
  "notification-service"
)

# Open each service in a new Terminal tab
for service in "${services[@]}"; do
  osascript <<EOF
tell application "Terminal"
    do script "cd $(pwd)/$service && ./mvnw spring-boot:run"
end tell
EOF
done
