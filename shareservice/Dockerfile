FROM java:8-jre

ADD ./target/shareservice.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config.jar"]

EXPOSE 6001