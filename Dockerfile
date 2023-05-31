#FROM openjdk:17-oracle
#EXPOSE 8080
#ADD target/springboot-image-nodb.jar springboot-image-nodb.jar
#ENTRYPOINT ["java","-jar","/springboot-image-nodb.jar"]

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080