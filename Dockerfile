FROM openjdk:8

COPY target/coffee.jar coffee.jar

ENTRYPOINT ["java", "-jar", "coffee.jar"]
