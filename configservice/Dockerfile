FROM java:8-jre
WORKDIR /app

ADD ./target/configservice.jar .
CMD ["java", "-Xmx200m", "-jar", "/app/configservice.jar"]

EXPOSE 8888