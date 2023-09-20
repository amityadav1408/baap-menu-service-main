FROM openjdk:17-jdk-slim
ADD target/baap-menu-0.0.1-SNAPSHOT.jar baap-menu-service-main.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "baap-menu-service-main.jar"]