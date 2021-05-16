FROM adoptopenjdk/maven-openjdk9 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean install spring-boot:repackage

FROM adoptopenjdk/openjdk9:latest
COPY --from=build /usr/src/app/target/baml-url-1.0-SNAPSHOT.jar /usr/src/app/baml-url-1.0-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/usr/src/app/baml-url-1.0-SNAPSHOT.jar"]