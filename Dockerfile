FROM ubuntu:22.04

RUN apt-get update
RUN apt-get install -y openjdk-17-jdk openjdk-17-jre maven

COPY target/TPUSA-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]