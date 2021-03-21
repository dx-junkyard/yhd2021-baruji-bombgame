#!/bin/bash

while true;
do
  curl -X POST http://127.0.0.1:8080/v1/timekeeper/1;
  sleep 2s;
done
