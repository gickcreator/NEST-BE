FROM amd64/amazoncorretto:17
WORKDIR /app
COPY ./build/libs/opensource-0.0.1-SNAPSHOT.jar /app/OPENSOURCE.jar
CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=dev", "OPENSOURCE.jar"]