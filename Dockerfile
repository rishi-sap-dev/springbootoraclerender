FROM openjdk:17-oracle
EXPOSE 8080
ADD target/springboot-image-nodb.jar springboot-image-nodb.jar
ENTRYPOINT ["java","-jar","/springboot-image-nodb.jar"]