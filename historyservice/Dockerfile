FROM java:8-jre
WORKDIR /app

ADD ./target/historyservice.jar .
CMD ["java", "-Xmx200m", "-jar", "/app/historyservice.jar"]

EXPOSE 6002


