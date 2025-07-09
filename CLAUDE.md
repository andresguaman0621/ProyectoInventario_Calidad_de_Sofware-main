# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview
This is a Java inventory management system built with Maven and following software quality practices. The project demonstrates SOLID principles, design patterns, and includes multiple static analysis tools for code quality assurance.

## Development Commands

### Build and Run
```bash
cd proyecto_inventario
mvn compile exec:java -Dexec.mainClass="proyecto.inventario.App"
```

### Testing
```bash
mvn test                 # Run all tests
mvn test -Dtest=ProductTest         # Run specific test class
mvn test -Dtest=ProductTest#testProductCreation   # Run specific test method
```

### Code Quality Analysis
The project includes several static analysis tools that run automatically:
- **Checkstyle**: Validates code style using Google coding standards
- **PMD**: Static code analysis for potential issues
- **SpotBugs**: Bug detection analysis
- **JaCoCo**: Code coverage reporting

Run all quality checks:
```bash
mvn clean verify
```

Run individual tools:
```bash
mvn checkstyle:check      # Code style validation
mvn pmd:check            # PMD analysis
mvn spotbugs:check       # SpotBugs analysis
mvn jacoco:report        # Generate coverage report
```

### Package Management
```bash
mvn clean compile        # Clean and compile
mvn clean package        # Build JAR file
```

## Architecture Overview

### Core Design Patterns
- **Singleton Pattern**: `Inventory` class uses thread-safe singleton with holder idiom
- **Strategy Pattern**: `InventoryReport` interface with multiple implementations
- **Interface Segregation**: Separate `InventoryReader` and `InventoryWriter` interfaces
- **Dependency Injection**: `InventoryPrinter` depends on abstractions, not concrete classes

### Package Structure
- `proyecto.inventario`: Main package with core classes
- `proyecto.inventario.report`: Report generation strategies
- `proyecto.inventario.repository`: Data access layer (though not fully utilized)

### Key Components

#### Core Classes
- **`Inventory`**: Singleton managing product storage, implements both reader and writer interfaces
- **`Product`**: Immutable data class with name, mutable quantity and price
- **`InventoryPrinter`**: Orchestrates report generation using strategy pattern

#### Interfaces
- **`InventoryReader`**: Read-only operations (`getProducts()`)
- **`InventoryWriter`**: Write operations (`addProduct()`)
- **`InventoryReport`**: Strategy interface for different report formats

#### Report Implementations
- **`ConsoleInventoryReport`**: Prints products to console
- **`CsvInventoryReport`**: Exports products to CSV file

### Data Flow
1. `App.main()` creates `Inventory` singleton instance
2. Products are added via `InventoryWriter` interface
3. `InventoryPrinter` reads products via `InventoryReader` interface
4. Reports are generated using strategy pattern implementations

## Code Quality Configuration
- **Checkstyle**: Uses `google_checks.xml` configuration
- **Java Version**: 11
- **Encoding**: UTF-8
- **Test Framework**: JUnit 3.8.1 (legacy version)

## File Generation
The application generates an `inventario.csv` file in the project root containing product data.

## Test Coverage
The test suite includes comprehensive coverage for:
- **ProductTest**: Tests Product class creation, getters, setters, and getDetails()
- **InventoryTest**: Tests Inventory singleton pattern, product addition, and data access
- **InventoryPrinterTest**: Tests printing functionality with mock objects
- **ConsoleInventoryReportTest**: Tests console output formatting
- **CsvInventoryReportTest**: Tests CSV file generation and formatting  
- **AppTest**: Tests main application flow and integration

Tests are designed to achieve 60%+ code coverage with JaCoCo and use JUnit 3.8.1 syntax for GitHub Actions compatibility.