# Docker multi-stage build

# 1. Building the App with Maven
FROM gradle:jdk8 as builder

ADD . /store
WORKDIR /store

# Just echo so we can see, if everything is there :)
RUN ls -l

# Run Maven build
RUN gradle clean build


FROM adoptopenjdk/openjdk8-openj9:alpine-slim as finalApp

MAINTAINER Vlad Marchenko

ENTRYPOINT ["sudo", "mkdir", "/app/"]

WORKDIR app

# Add Spring Boot app.jar to Container
COPY --from=builder /store/build/libs/store-0.0.1-SNAPSHOT.jar /app

ENV JAVA_OPTS=""

# Fire up our Spring Boot app by default
ENTRYPOINT java $JAVA_OPTS -Xshareclasses -Xquickstart -jar /app/store-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java", "-jar", "/app/store-0.0.1-SNAPSHOT.jar"]