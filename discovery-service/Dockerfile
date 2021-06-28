FROM openjdk:11
EXPOSE 8761
ADD target/discovery-microservice.jar discovery-microservice.jar
ENTRYPOINT ["java","-jar","/discovery-microservice.jar"]