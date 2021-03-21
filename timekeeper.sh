#!/bin/bash

host=$1

while true;
do
  curl -X POST http://${host}/v1/timekeeper/1;
  sleep 2s;
done
