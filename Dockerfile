FROM gradle:8.7.0-jdk21-alpine AS build
COPY --chown=gradle:gradle . /home/app
WORKDIR /home/app
RUN gradle clean build --no-daemon -i -x test -x javadoc

FROM eclipse-temurin:21-jre-alpine
COPY --from=build /home/app/ss-web/build/libs/ss-web.jar /usr/local/lib/ss/app.jar
RUN apk update && apk upgrade libssl3 libcrypto3
RUN addgroup -S sw && adduser -S sw -G sw
USER sw
WORKDIR /usr/local/lib/ss
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
