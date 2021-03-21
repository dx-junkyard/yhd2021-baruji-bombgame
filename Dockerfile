FROM openjdk:15.0.2-jdk

ADD target/*-SNAPSHOT.jar /apps/app.jar
COPY src/main/resources/application-prd.properties /apps/config/
VOLUME /apps

COPY ./entrypoint.sh /
COPY ./timekeeper.sh /

RUN chmod +x /entrypoint.sh

CMD [ "/entrypoint.sh" ]