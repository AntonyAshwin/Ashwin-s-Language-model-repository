FROM eclipse-temurin:21-jre-jammy
COPY *.jar app.jar
CMD ["java", "-jar", "app.jar", "--server.port=7860"]