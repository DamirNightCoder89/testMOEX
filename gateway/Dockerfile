FROM java:8-jre
WORKDIR /app

ADD ./target/gateway.jar .
CMD ["java", "-Xmx200m", "-jar", "/app/gateway.jar"]

EXPOSE 9191