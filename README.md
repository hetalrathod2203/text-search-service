# text-search-service
Text Search Service [Spring Boot - Java]
Search a text from all the documents/text stored in the Database. In Memory cache (ehCache) is used to store frequently search key words.

## Requirements

For building and running the application you need:

- [JDK 1.5](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

```shell
mvn clean install
java -jar textsearch-service-0.0.1-SNAPSHOT.jar
```

## Swagger

The aplication contains swagger document.
```shell
http://localhost:8080/swagger-ui/index.html#/text-search-controller
```

The Apis supported:

* Upload a file
* Create a text content
* Search all documents based on a 'keyword'

