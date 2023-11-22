FROM openjdk:11-jdk-slim
WORKDIR /app
ARG JAR_FILE=/build/libs/*.jar
COPY ${JAR_FILE} sundo.jar
ENV TZ=Asia/Seoul
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "sundo.jar"]