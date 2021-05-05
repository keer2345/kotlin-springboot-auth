<h1> [Kotlin API Authentication using JWT | Spring Boot JWT Authentication](https://youtu.be/kZGtO23Wr3E?list=PLlameCF3cMEtCGZW2djY46Dl20-uSNJba)</h1>

<!-- markdown-toc start - Don't edit this section. Run M-x markdown-toc-refresh-toc -->
**Table of Contents**

- [Materials](#materials)
- [Spring Boot Dependencies](#spring-boot-dependencies)
- [Database connection of MySQL](#database-connection-of-mysql)
    - [Databaase Server](#databaase-server)
    - [Database Environment](#database-environment)
    - [Database Configuration](#database-configuration)
- [Spring Security](#spring-security)
- [JSON Web Tokens](#json-web-tokens)
- [Run Project](#run-project)
- [API Test](#api-test)

<!-- markdown-toc end -->


Learn how to login with Kotlin with Java Spring Boot Framework and handle the JWT Token. We will Login using JWT( JSON Web Token ) which is the standard method for SPA Authentications. We will not use the traditional "Bearer method" but instead we will login using HttpOnly cookies which is a more secure authentication.

## Materials
- [Spring Boot Security Auto-Configuration](https://www.baeldung.com/spring-boot-security-autoconfiguration)
- [Conditionally Disabling or Overriding Spring Boot Security Auto-Configuration](https://ravthiru.medium.com/conditionally-disabling-or-overriding-spring-boot-security-auto-configuration-94f67947334c)

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
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:33060/kotlin_springboot_auth
spring.datasource.username=springboot
spring.datasource.password=spring123456

spring.jpa.properties.hibernate.hbm2ddl.auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.show-sql= true
```
## Spring Security
If we want to ignore *Spring Security*, we could do it in file *KotlinSpringbootAuthApplication.kt* follow here:
``` kotlin
// ...

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class KotlinSpringbootAuthApplication

// ...
```

## JSON Web Tokens

> [JWT](https://jwt.io/)

Add dependencies in file `build.gradle.kts`:
``` kotlin
dependencies {
    // ...
    implementation("io.jsonwebtoken:jjwt:0.9.1")
}
```

## Run Project
``` shell
./gradlew bootRun
```

## API Test
Recommend API tools: Postman
Others: [hoppscotch](https://github.com/hoppscotch/hoppscotch) (postwoman) to test API, or 
alternative with [Awesome API Tools](https://github.com/elangosundar/awesome-api-tools).

If need to use Cross Requiest:
- Add `@CrossOrigin` to *Controller* class:
``` kotlin
@RestController
@RequestMapping("api")
@CrossOrigin
class AuthController(private val userService: UserService) {
    
    // ...
}
```
- Or, add `@CrossOrigin` to _*Mapping_ function / method:
``` kotlin
@RestController
@RequestMapping("api")
class AuthController(private val userService: UserService) {

    @PostMapping("register")
    @CrossOrigin
    fun register(@RequestBody user: User): ResponseEntity<User> = ResponseEntity.ok(this.userService.save(user))
    
    // ...
}
```
- Or, create file *WebConfig.kt*:
``` kotlin
package com.keer.auth

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**").allowedOrigins(
            "http://localhost:3000",
            "http://localhost:8080", "http://localhost:4200"
        ).allowCredentials(true)
    }
}
```
