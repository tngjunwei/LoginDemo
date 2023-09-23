FROM maven:latest as BUILD
WORKDIR /usr/src
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:17-ea-slim
WORKDIR /usr/src
COPY --from=BUILD /usr/src/target  .
COPY wait-for-it.sh .
ENTRYPOINT ["./wait-for-it.sh", "db:3306", "--", "java", "-jar", "login_demo-0.0.1-SNAPSHOT.jar"]
