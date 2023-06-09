FROM openjdk:19-alpine
WORKDIR /opt
COPY build/libs/CryptoProject-0.0.1-SNAPSHOT.jar	/opt/crypto-service.jar
EXPOSE 8080
ENTRYPOINT java -jar /opt/crypto-service.jar
