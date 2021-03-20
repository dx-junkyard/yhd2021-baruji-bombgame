#!/bin/bash

exec java $JAVA_OPTS $AGENT_PARAM \
  -Duser.timezone=Asia/Tokyo \
  -Dspring.config.location=${SPRING_CONFIG_LOCATION} \
  -Dspring.profiles.active=${SPRING_PROFILE} \
  -Djava.security.egd=file:/dev/./urandom \
  -jar /apps/app.jar