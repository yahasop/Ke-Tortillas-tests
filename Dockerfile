FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY ./target/*.war /app/tortillas.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "tortillas.war"]
