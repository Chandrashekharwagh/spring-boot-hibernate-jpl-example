# Spring Boot Hibernate JPL Example with H2 Database

This example demonstrates the integration of Spring Boot with Hibernate JPL using H2 as an in-memory database for development and testing purposes.

## Table of Contents
- [Technologies Used](#technologies-used)
- [Project Setup](#project-setup)
- [Configuration](#configuration)
- [Project Structure](#project-structure)
- [Running the Application](#running-the-application)
- [Key Components](#key-components)
- [H2 Database Configuration](#h2-database-configuration)
- [Best Practices](#best-practices)

## Technologies Used
- Spring Boot
- Hibernate JPL
- Maven (for dependency management)
- H2 Database (in-memory database)

## Project Setup
1. Ensure you have Java JDK 11 or later installed.
2. Install Maven if not already installed.
3. Clone this repository: `git clone https://github.com/Chandrashekharwagh/spring-boot-hibernate-jpl-example.git`
4. Navigate to the project directory: `cd spring-boot-hibernate-jpl-example`
5. Build the project: `mvn clean install`

## Configuration
The main configuration file is `application.properties` located in `src/main/resources`. Here's a sample configuration for H2:

```properties
# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# H2 Console Configuration
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## Project Structure
```
src
├── main
   ├── java
   │   └── com
   │       └── yourcompany
   │           └── project
   │               ├── controller
   │               ├── model
   │               ├── repository
   │               ├── service
   │               └── Application.java
   └── resources
      └── application.properties

```

## Running the Application
To run the application, use the following command:

```
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## Key Components

### 1. Entity
Define your JPA entities in the `model` package:

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    
    // Getters and setters
}
```

### 2. Repository
Create repositories in the `repository` package:

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods if needed
}
```

### 3. Service
Implement business logic in the `service` package:

```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    // Other service methods
}
```

### 4. Controller
Create REST endpoints in the `controller` package:

```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
    
    // Other endpoints
}
```

## H2 Database Configuration
1. H2 is an in-memory database, so no separate database creation is required.
2. The H2 console is enabled and can be accessed at `http://localhost:8080/h2-console`.
3. Use the following details to log in to the H2 console:
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Username: `sa`
   - Password: `password`

## Best Practices
1. Use DTOs (Data Transfer Objects) to separate your API models from database entities.
2. Implement proper exception handling and validation.
3. Use Spring Profiles for different environments (dev, test, prod).
4. Write unit tests for your services and repositories.
5. Use transactions for operations that require atomic execution.
6. Implement proper logging using SLF4J and Logback.
7. For production, consider using a persistent database instead of H2.

For more detailed information on Spring Boot and Hibernate JPA, refer to the official documentation:
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Data JPA Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [H2 Database Engine](https://www.h2database.com/html/main.html)

## License

This project is licensed under the MIT License - see the `LICENSE.md` file for details.
