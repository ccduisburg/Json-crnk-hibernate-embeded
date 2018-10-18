#!/usr/bin/env bash
sudo fuser -k 8080/tcp
sudo fuser -k 9090/tcp
export JAVA_HOME=/usr/java/jdk1.8.0_181-amd64
mvn package
java -jar target/json-api-crnk-relationship-0.0.1-SNAPSHOT.jar --debug