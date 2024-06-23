#!/bin/bash

mvn clean install

# Build room-occupancy-manager image
echo "Building room-occupancy-manager Docker image..."
docker build -t room-occupancy-manager  .


if [ $? -ne 0 ]; then
  echo "Docker build failed. Is your docker currently running on this machine ?"
  exit 1
fi

# Run the room-occupancy-manager image container
echo "Starting Docker container..."
docker run -d -p 8080:8080 room-occupancy-manager

echo "Please wait for 10 seconds till room-occupancy-manager swagger page will be available at: http://localhost:8080/room-occupancy-manager/swagger-ui.html"

start http://localhost:8080/room-occupancy-manager/swagger-ui.html