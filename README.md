## Ad Selection  - Microservices

#### Goal

The Goal is to implement a new ad-selection service which plays a critical role in the ecosystem of advertising platform. 

#### Description

It is implemented by two microservices, the Pacing Service and the Request Service. 
The Requests service receives ad selection requests from the Publisher, and returns the selected ad. 
The Publisher then sends an impression request to the Pacing Service (Assumption: we receive an impression for each request). 
The Pacing Service is responsible to keep track of the impressions and to expose API to the Request Service regarding the pacing status of the ads.


## Requirements

* [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or later
* [Maven 3.0+](http://maven.apache.org/download.cgi)

## Build with Maven

* [Welcome to Apache Maven](https://maven.apache.org/)
* [Building Java Projects with Maven](https://spring.io/guides/gs/maven/)

## Build with Maven

* Run this maven commands under the project's workspace folder using the terminal:

```
1. Common library

  cd ad-selection/common
  mvn clean install
```

```
2. Pacing service
  
  cd ad-selection/pacing-service/pacing-service-model
  mvn clean install

  cd ad-selection/pacing-service/pacing-service-client
  mvn clean install

  cd ad-selection/pacing-service/pacing-service-application
  mvn clean install
```

```
3. Request service
  
  cd ad-selection/request-service/request-service-model
  mvn clean install

  cd ad-selection/request-service/request-service-client
  mvn clean install

  cd ad-selection/request-service/request-service-application
  mvn clean install
```

## Run the services

* cd into project-root-folder using your terminal.

* Using Maven you can run the application using **mvn spring-boot:run**. 

```
1. Pacing service
  cd ad-selection/pacing-service/pacing-service-application
  mvn spring-boot:run
```

```
2. Request service
  cd ad-selection/request-service/request-service-application
  mvn spring-boot:run
```

* Or you can build an executable JAR file with **mvn clean package** and run the JAR by typing:

```
  java -jar ad-selection/pacing-service/pacing-service-application/target/pacing-service-application-1.0.0.jar
  java -jar ad-selection/request-service/request-service-application/target/request-service-application-1.0.0.jar  
```

## Test the services

* Take into consideration the following delivery-plan file: ad-selection\request-service\request-service-application\src\main\resources\delivery-plan.json

* Run both services, and visit the following urls using Postman or other API client:

Select Ad
```
POST localhost:8080/request-service/selectAd 
request body: ["a1", "a2", "c1", "b2"]
```

Add Impression: 
```
POST localhost:8090/pacing-service/ads/a1/impressions
```

Get Impression:
```
GET localhost:8090/pacing-service/ads/a1/impressions
```

## H2 Console

http://localhost:8090/pacing-service/h2-console
```
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:PATH_TO_PROJECT\data\h2db
User Name: sa
Password:
```