# syntax=docker/dockerfile:1

FROM maven:3.9.8-eclipse-temurin-17 as builder
WORKDIR /build
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -e -ntp -DskipTests dependency:go-offline
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -q -e -ntp -DskipTests package

FROM eclipse-temurin:17-jre as runtime
WORKDIR /app
COPY --from=builder /build/target/yourshop-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-XX:+UseContainerSupport","-jar","/app/app.jar"]