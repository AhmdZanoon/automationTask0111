
# Automation Testing Project

## Project Overview
This is an automation testing project built with Java, using Selenium for web UI testing, TestNG as the test framework, Rest Assured for API testing, Cucumber for behavior-driven development (BDD) testing, and Allure for test reporting. The project is structured to support both API and web UI tests with a clear separation of concerns.

## Prerequisites
- **Java Development Kit (JDK)** 21 
- **Maven** for managing dependencies.
- **Docker** (to run Docker file locally If desired).
- **Supported Browser** (e.g., Chrome or Firefox) and its corresponding WebDriver.

## Installation and Setup
1. Clone the repository:
   ```bash
   git clone <https://github.com/AhmdZanoon/automationTask0111.git>
   ```
2. Navigate to the project directory:
   ```bash
   cd <project_directory>
   ```
3. Install dependencies using Maven:
   ```bash
   mvn clean install
   ```

## Project Structure
- **src/main/java**
    - **pages.webPages**: Contains page classes for the "Sauce Demo" website, following the Page Object Model. Each page (e.g., `SauceDemoCartPage`, `SauceDemoLoginPage`) represents a specific web page.
    - **tools**: Utility classes like `Assistant` and `WebConfigurationManager` for supporting configuration and reusable functions.
    - **utilities**: Classes for handling property files (`ApiProperties`, `WebProperties`).

- **src/test/java**
    - **apiTests**: Contains API test classes using Rest Assured, such as `BreedsEndpointTest` and `ImagesEndpointTest`.
    - **webTests**
        - **cucumberSteps**: Step definitions for Cucumber feature files.
        - **cucumberTestRunner**: Runner class to execute Cucumber tests.
        - **features**: Cucumber feature files (e.g., `ScenarioOne.feature` and `ScenarioTwo.feature`) that define test scenarios.

- **resources**
    - **properties**: Contains configuration files, `ApiProperties.properties` and `webProperties.properties`, for API and web tests.

## Running Tests
**API Tests**: Run all API tests using the following Maven command:
   ```bash
   mvn test -Dtest=apiTests.**
   ```
  ### list of API Test Cases 
- test cases cover 2 endpoint from thedogapi.com ( images endpoint and breeds endpoint )
- testing CRUD operations on Images endpoint
- testing GET http Method on Breeds endpoint with and without authentication  

 **Web UI Tests**: Run all web UI tests:
   ```bash
   mvn test -Dtest=webTests.**
   ```
  
## Configurations
- **ApiProperties.properties**: Configuration for API endpoints.
- **webProperties.properties**: Configuration for web settings (e.g., browser type, base URL).

## Reporting 
to run test report after end of test execution, open cmd in project directory and run following command
```bash
  mvn allure:report
```

