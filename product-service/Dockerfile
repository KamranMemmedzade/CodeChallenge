FROM openjdk:11
EXPOSE 8082
ADD target/product-microservice.jar product-microservice.jar
ENTRYPOINT ["java","-jar","/product-microservice.jar"]