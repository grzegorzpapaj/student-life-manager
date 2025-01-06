FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .
RUN ./mvnw clean install
CMD ["./mvnw", "spring-boot:run"]
