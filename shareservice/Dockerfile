FROM java:8-jre
WORKDIR /app

ADD ./target/shareservice.jar .
CMD ["java", "-Xmx200m", "-jar", "/app/shareservice.jar"]

EXPOSE 6001


