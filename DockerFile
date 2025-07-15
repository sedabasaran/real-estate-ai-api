# Base image: Java 17 with minimal footprint
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy Maven wrapper and project files into container
COPY . .

# Make mvnw executable
RUN chmod +x mvnw

# Build the Spring Boot app
RUN ./mvnw clean package -DskipTests

# Run the compiled JAR
CMD ["java", "-jar", "target/real-estate-ai-api-0.0.1-SNAPSHOT.jar"]
