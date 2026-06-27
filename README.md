# Java Samples

Modern Java sample applications demonstrating best practices, design patterns, and contemporary development practices with Gradle and Kotlin.

## 🎯 Overview

This repository contains professional Java examples showcasing:

- **Modern Java Features** (Java 17+): Records, sealed classes, pattern matching
- **Design Patterns**: Repository, Service, Dependency Injection patterns
- **Kotlin Integration**: Demonstrating Kotlin for Java projects
- **Comprehensive Testing**: JUnit 5, Mockito, AssertJ
- **Code Quality**: Checkstyle linting and JaCoCo code coverage
- **CI/CD Pipeline**: GitHub Actions workflow for automated testing

## 📋 Project Structure

```
java-samples/
├── src/
│   ├── main/
│   │   ├── java/com/example/patterns/
│   │   │   ├── User.java              # Java Record demonstrating immutability
│   │   │   ├── UserRepository.java    # Repository interface (Repository pattern)
│   │   │   ├── InMemoryUserRepository.java  # Repository implementation
│   │   │   ├── UserService.java       # Service layer with dependency injection
│   │   │   └── UserNotFoundException.java   # Custom exception
│   │   └── kotlin/com/example/patterns/
│   │       └── Calculator.kt          # Kotlin demonstrating sealed classes, when expressions
│   ├── test/
│   │   ├── java/com/example/patterns/
│   │   │   ├── UserServiceTest.java
│   │   │   └── InMemoryUserRepositoryTest.java
│   │   └── kotlin/com/example/patterns/
│   │       └── CalculatorTest.kt
├── config/
│   └── checkstyle/
│       └── checkstyle.xml             # Code style configuration
├── .github/
│   └── workflows/
│       └── ci-cd.yml                  # GitHub Actions CI/CD pipeline
├── build.gradle                       # Gradle build configuration
├── settings.gradle                    # Gradle settings
├── gradlew / gradlew.bat             # Gradle wrapper
└── README.md                          # This file
```

## 🚀 Getting Started

### Prerequisites

- **JDK 17+** (or download automatically via Gradle)
- **Gradle 8.5+** (or use the included wrapper)

### Building the Project

```bash
./gradlew build
```

This will:
- Compile Java and Kotlin sources
- Run Checkstyle linting
- Execute all tests with JUnit 5
- Generate JaCoCo code coverage reports

### Running Tests Only

```bash
./gradlew test
```

### Running Linting Only

```bash
./gradlew checkstyleMain checkstyleTest
```

### Viewing Code Coverage Reports

After running `./gradlew build`, open the coverage report:

```bash
open build/reports/jacoco/test/html/index.html
```

## 📚 Design Patterns Demonstrated

### 1. **Repository Pattern** (`UserRepository.java`)
Abstract data access layer to decouple business logic from persistence:
- Interface definition for data access operations
- In-memory implementation example
- Testable abstractions

### 2. **Service Pattern** (`UserService.java`)
Business logic layer with clear responsibilities:
- Dependency injection through constructor
- Transaction-like operations
- Comprehensive logging with SLF4J

### 3. **Immutability with Records** (`User.java`)
Modern Java 17+ Records for data objects:
- Automatic `equals()`, `hashCode()`, `toString()`
- Immutable by design
- Compact constructor for validation

### 4. **Functional Programming with Kotlin** (`Calculator.kt`)
Kotlin sealed classes and functional patterns:
- Type-safe error handling with sealed classes
- Extension functions for clean API
- When expressions for pattern matching

## 🧪 Testing Infrastructure

### Test Coverage

- **JUnit 5**: Modern testing framework with `@DisplayName` annotations
- **Mockito**: Mocking framework (ready for complex scenarios)
- **AssertJ**: Fluent assertions for readable tests

### Test Files

- `UserServiceTest.java`: Tests for service layer (9 test cases)
- `InMemoryUserRepositoryTest.java`: Tests for repository (8 test cases)
- `CalculatorTest.kt`: Kotlin test examples (11 test cases)

Total: **28 test cases** covering happy paths and error scenarios

### Code Coverage

JaCoCo generates detailed coverage reports in `build/reports/jacoco/test/`:
- HTML reports with line and branch coverage
- CSV summary for CI/CD integration
- XML format for SonarQube integration

## 🔧 Technologies & Dependencies

### Build Tools
- **Gradle 8.5**: Build automation
- **Kotlin Gradle Plugin 1.9.21**: Kotlin compilation support

### Languages & Frameworks
- **Java 17**: Primary language
- **Kotlin 1.9.21**: Functional programming examples

### Testing
- **JUnit 5 (Jupiter)**: Test framework
- **Mockito 5.3.1**: Mocking framework
- **AssertJ 3.24.1**: Fluent assertions

### Code Quality
- **Checkstyle 10.12.1**: Code style enforcement
- **JaCoCo 0.8.10**: Code coverage analysis

### Logging
- **SLF4J 2.0.9**: Logging facade
- **Logback 1.4.14**: Logging implementation

### Utilities
- **Gson 2.10.1**: JSON processing

## 🔄 CI/CD Pipeline

The repository includes a GitHub Actions workflow (`.github/workflows/ci-cd.yml`) that:

1. **Builds** the project on push and pull requests
2. **Lints** code with Checkstyle
3. **Runs** all tests with JUnit 5
4. **Generates** JaCoCo code coverage reports
5. **Uploads** coverage to Codecov
6. **Archives** test results and coverage reports
7. **Publishes** test results as check runs

### Workflow Trigger

- Runs on: Push to `main` or `develop`, or PR to these branches
- Environment: Ubuntu latest with JDK 17

## 📖 Development Guidelines

### Code Style

The project follows standard Java/Kotlin conventions enforced by Checkstyle:
- 4-space indentation
- Line length max 120 characters
- Standard naming conventions (camelCase, PascalCase, CONSTANT_CASE)

### Writing Tests

1. Use `@DisplayName` for descriptive test names
2. Follow AAA pattern: Arrange, Act, Assert
3. Use fluent assertions with AssertJ
4. Test both happy paths and error cases

Example:
```java
@Test
@DisplayName("Should create a new user successfully")
void testCreateUser() {
    // Arrange
    String id = "user1";
    
    // Act
    User user = service.createUser(id, "John Doe", "john@example.com");
    
    // Assert
    assertThat(user.id()).isEqualTo(id);
}
```

### Adding New Code

1. Implement in `src/main/`
2. Add tests in `src/test/`
3. Ensure Checkstyle passes: `./gradlew checkstyleMain`
4. Run tests: `./gradlew test`
5. Check coverage: `./gradlew jacocoTestReport`

## 📊 Code Quality Metrics

Current project statistics:
- **Java Files**: 5 (User, UserRepository, InMemoryUserRepository, UserService, UserNotFoundException)
- **Kotlin Files**: 1 (Calculator with extension functions)
- **Test Classes**: 3 (Java: 2, Kotlin: 1)
- **Test Methods**: 28 total
- **Code Coverage**: Target 80%+

## 🛠️ Gradle Tasks

Common Gradle commands:

```bash
./gradlew build              # Clean build with all checks
./gradlew test               # Run tests only
./gradlew check              # Run all checks (Checkstyle, tests)
./gradlew clean              # Remove build artifacts
./gradlew jacocoTestReport   # Generate coverage report
./gradlew checkstyleMain     # Lint production code
./gradlew compileJava        # Compile Java only
./gradlew compileKotlin      # Compile Kotlin only
```

## 📝 License

This is a sample project. Use freely for learning and reference.

## 🤝 Contributing

This is a reference implementation. Feel free to fork and extend with additional patterns and examples.

---

**Last Updated**: June 2024  
**Java Version**: 17+  
**Gradle Version**: 8.5+  
**Kotlin Version**: 1.9.21+
