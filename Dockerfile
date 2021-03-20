FROM openjdk:15.0.2-slim

ADD target/*-SNAPSHOT.jar /apps/app.jar
COPY ./entrypoint.sh /
RUN chmod +x /entrypoint.sh

CMD [ "entrypoint.sh" ]