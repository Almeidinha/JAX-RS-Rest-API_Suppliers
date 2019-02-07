#!/bin/sh
mvn clean package && docker build -t com.neomind.rest/supplier .
docker rm -f supplier || true && docker run -d -p 8080:8080 -p 4848:4848 --name supplier com.neomind.rest/supplier 
