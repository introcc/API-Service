# API Service

## Protobuf
```
mvnw protobuf:compile
mvnw protobuf:compile-custom
```

## Run
```
mvnw sping-boot:run
```

## Error Codes
| Code | Description                              |
|------|------------------------------------------|
| 1000 | Invalid request                          |
| 1001 | Email required                           |
| 1002 | Username required                        |
| 1003 | Password required                        |
| 1004 | Invalid email address                    |
| 1005 | Email already registered                 |
| 1006 | Username already registered              |
| 1007 | Password must have at least 8 characters |
| 1008 | Username must be less than 32 characters |
| 1009 | Email or password incorrect              |
| 1010 | Old password is incorrect                |

## Swagger
* [http://localhost:8098/swagger-ui.html](http://localhost:8098/swagger-ui.html)
* [Document](http://springfox.github.io/springfox/docs/current/)

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

