# [Kotlin API Authentication using JWT | Spring Boot JWT Authentication](https://youtu.be/kZGtO23Wr3E?list=PLlameCF3cMEtCGZW2djY46Dl20-uSNJba)

Learn how to login with Kotlin with Java Spring Boot Framework and handle the JWT Token. We will Login using JWT( JSON Web Token ) which is the standard method for SPA Authentications. We will not use the traditional "Bearer method" but instead we will login using HttpOnly cookies which is a more secure authentication.


## Spring Boot Dependencies
- Spring Web
- Spring Security
- Spring Data JPA
- Mysql Driver

## Database connection of MySQL 
### Databaase Server
Database Server with [docker-compose](https://github.com/keer2345/docker-databases-with-adminer).
### Database Environment
```
create database kotlin_springboot_auth default charset utf8 collate utf8_general_ci;
create user 'springboot'@'%' identified by 'spring123456';
grant all privileges on kotlin_springboot_auth.* to "springboot"; 
flush privileges;
```
### Database Configuration
`application.properties`:
``` properties
spring.datasource.url=jdbc:mysql://localhost:33060/kotlin_springboot_auth?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC
spring.datasource.username=springboot
spring.datasource.password=spring123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.properties.hibernate.hbm2ddl.auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.show-sql= true
```

## Run Project
``` shell
./gradlew bootRun
```