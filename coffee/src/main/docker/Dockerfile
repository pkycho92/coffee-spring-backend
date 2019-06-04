FROM openjdk:8u131-jdk-alpine

MAINTAINER Peter C

EXPOSE 8080

WORKDIR /usr/local/bin/

COPY maven/coffee-2.0.6.RELEASE.jar webapp.jar

CMD ["java","run","-jar","webapp.jar"]