FROM openjdk:17-oracle
WORKDIR /server
RUN ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime && echo Asia/Seoul > /etc/timezone
COPY build/libs/mz2mo-server-0.0.1-SNAPSHOT.jar mz2mo-server.jar
CMD ["java", "-jar", "mz2mo-server.jar", "--spring.profiles.active=${SPRING_PROFILE}"]