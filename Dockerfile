#FROM openjdk:8
FROM openjdk:17-jdk-slim
RUN mkdir springapp
WORKDIR /springapp
#ADD target/GeneralProgExec-2.0.0-SNAPSHOT.jar GeneralProgExec-2.0.0-SNAPSHOT.jar
ADD target/spring-boot-ecommerce-0.0.1-SNAPSHOT.jar spring-boot-ecommerce-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "spring-boot-ecommerce-0.0.1-SNAPSHOT.jar"]
