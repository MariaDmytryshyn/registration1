FROM openjdk:11-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV SPRING_PROFILES_ACTIVE="kubernetes"
ENTRYPOINT ["java","-jar","/app.jar"]