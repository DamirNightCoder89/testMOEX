FROM java:8-jre

ADD ./target/registry.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config.jar"]

EXPOSE 8761