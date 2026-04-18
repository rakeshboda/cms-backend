# ---------- Stage 1: Build the app ----------
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy pom first for dependency caching
COPY pom.xml .
RUN mvn -B -q -e -DskipTests dependency:go-offline

# Copy source and build
COPY src ./src
RUN mvn -B -q -DskipTests package

# ---------- Stage 2: Run the app ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy jar from builder
COPY --from=builder /app/target/*.jar app.jar

# Render provides PORT env var
EXPOSE 8080
ENV PORT=8080

# Run the jar
ENTRYPOINT ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]