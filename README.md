# YourShop - Enterprise E-commerce Platform

Java 17, Spring Boot 3.x, Maven. Modules: auth, user, product, category, vendor, cart, order, payment, review, search.

## Quick Start (Dev)
- Prerequisites: Java 17+, Maven 3.9+, Docker
- Configure `src/main/resources/application.yml`

```bash
mvn clean package
java -jar target/yourshop-0.0.1-SNAPSHOT.jar
```

## Docker
```bash
docker compose up --build
```
- App: http://localhost:8080
- MySQL: localhost:3306 (user/password in compose)
- Redis: 6379
- Elasticsearch: 9200

## Authentication
- POST `/api/auth/register`
- POST `/api/auth/login`
- GET `/api/auth/me`

Use JWT in `Authorization: Bearer <token>` header.

## Product Search
- GET `/api/products?q=&categoryId=&minPrice=&maxPrice=&page=&size=&sort=`
- Pagination and sorting supported. Uses JPA Specifications; ready to extend for Elasticsearch.

## Tests
- Unit tests for AuthService and ProductService
- Integration tests with Testcontainers (MySQL)

## Tech
- Spring Web, Security (JWT/BCrypt), Data JPA, Validation
- MapStruct for DTO mapping
- MySQL, Redis, Elasticsearch (optional)

## Notes
- This is a reference architecture with clear boundaries: controllers -> services -> repositories -> entities/DTOs.
- Global exception handling via `@ControllerAdvice`.