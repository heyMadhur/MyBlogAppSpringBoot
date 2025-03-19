FROM openjdk:17

WORKDIR /app

COPY target/BlogApp-0.0.1-SNAPSHOT.jar /app/BlogApp-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "BlogApp-0.0.1-SNAPSHOT.jar"]