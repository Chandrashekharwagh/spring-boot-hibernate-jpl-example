# Spring Boot with Jakarta Persistence Layer and H2 Database

This project demonstrates the integration of Spring Boot with Jakarta Persistence Layer (formerly JPA) using H2 as an in-memory database, featuring separate repository interfaces.

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
- Jakarta Persistence
- Spring Data JPA
- Maven (for dependency management)
- H2 Database (in-memory database)

## Project Setup
1. Ensure you have Java JDK 17 or later installed (required for Jakarta EE 9+).
2. Install Maven if not already installed.
3. Clone this repository: `git clone https://github.com/Chandrashkhareagh/spring-boot-jpl-example.git`
4. Navigate to the project directory: `cd spring-boot-jpl-example`
5. Build the project: `mvn clean install`

## Configuration
(Configuration section remains the same as in the previous version)

## Project Structure
```
src
├── main
│   ├── java
  │   └── com
  │       └── yourcompany
  │           └── project
  │               ├── controller
  │               ├── model
  │               ├── repository
  │               │   ├── UserRepository.java
  │               │   └── UserRepositoryImpl.java
  │               ├── service
  │               └── Application.java
  └── resources
      └── application.properties

```

## Running the Application
(Running the Application section remains the same as in the previous version)

## Key Components

### 1. Entity
(Entity section remains the same as in the previous version)

### 2. Repository
Create separate repository interfaces in the `repository` package:

```java
// UserRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Standard JPA methods are inherited
    User findByEmail(String email);
    List<User> findByLastName(String lastName);
}

// UserRepositoryImpl.java
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCustom {
    List<User> findByNameContaining(String name);
    List<User> findActiveUsers();
    void updateUserStatus(Long userId, boolean isActive);
}
```

In this structure:
- `UserRepository` extends `JpaRepository` to inherit standard CRUD operations and defines some common query methods.
- `UserRepositoryImpl` defines additional custom methods that may require more complex logic.

### 3. Service
The service can use both repositories:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserRepositoryCustom userRepositoryCustom;
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    public List<User> findUsersByNameContaining(String name) {
        return userRepositoryCustom.findByNameContaining(name);
    }
    
    public List<User> findActiveUsers() {
        return userRepositoryCustom.findActiveUsers();
    }
    
    // Other service methods
}
```

### 4. Controller
(Controller section remains the same as in the previous version)

## H2 Database Configuration
(H2 Database Configuration section remains the same as in the previous version)

## Best Practices
1. Use DTOs (Data Transfer Objects) to separate your API models from database entities.
2. Implement proper exception handling and validation.
3. Use Spring Profiles for different environments (dev, test, prod).
4. Write unit tests for your services and repositories.
5. Use `@Transactional` for operations that require atomic execution.
6. Implement proper logging using SLF4J and Logback.
7. For production, consider using a persistent database instead of H2.
8. Use Jakarta Persistence annotations (`jakarta.persistence.*`) instead of the older `javax.persistence.*`.
9. Separate repository interfaces based on functionality for better organization and modularity.
10. Use `@Repository` annotation on repository interfaces to indicate they are Spring components.
11. Consider using Spring Data JPA's query methods naming conventions for simple queries in the main repository.
12. Use the custom repository interface for more complex operations that may require manual implementation.

For more detailed information, refer to the official documentation:
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Data JPA Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Jakarta Persistence Specification](https://jakarta.ee/specifications/persistence/)
- [H2 Database Engine](https://www.h2database.com/html/main.html)
## License

This project is licensed under the MIT License - see the `LICENSE.md` file for details.
