FROM java:8-jre
WORKDIR /app

ADD ./target/registry.jar .
CMD ["java", "-Xmx200m", "-jar", "/app/registry.jar"]

EXPOSE 8761