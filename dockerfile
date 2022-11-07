FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/DPMS_Research_Sample-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]