FROM amazoncorretto:21-alpine
RUN apk add --no-cache wget

WORKDIR /app

COPY target/*.jar app.jar

# We don't need to access this service out of container
#EXPOSE 1234

# Define the entry point to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
