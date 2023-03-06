FROM openjdk:19-jdk

COPY ./target/simData-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["sh", "-c", "java -XX:InitialRAMPercentage=50 -XX:MaxRAMPercentage=70 -XshowSettings $JAVA_OPTS -jar app.jar"]