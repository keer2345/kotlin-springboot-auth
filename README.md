# [Kotlin API Authentication using JWT | Spring Boot JWT Authentication](https://youtu.be/kZGtO23Wr3E?list=PLlameCF3cMEtCGZW2djY46Dl20-uSNJba)

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
### 
If we want to ignore *Spring Security*, we could do it in file *KotlinSpringbootAuthApplication.kt* follow here:
``` kotlin
// ...

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class KotlinSpringbootAuthApplication

// ...
```

## Json Web Token ([JWT](https://jwt.io/))
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
I use [hoppscotch](https://github.com/hoppscotch/hoppscotch) (postwoman) to test API, or 
alternative with [Awesome API Tools](https://github.com/elangosundar/awesome-api-tools).

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

