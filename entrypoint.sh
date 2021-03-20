#!/bin/bash

exec java $JAVA_OPTS \
  -Duser.timezone=Asia/Tokyo \
  -Dspring.config.location=${SPRING_CONFIG_LOCATION} \
  -Dspring.profiles.active=${SPRING_PROFILE} \
  -jar /apps/app.jar