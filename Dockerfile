# Use a lightweight Java image
FROM openjdk:17-jdk-slim

# Set working directory in the container
WORKDIR /app

# Copy the jar into the container
COPY target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
