
Certainly! Here's the README formatted with proper Markdown syntax:

markdown
Copy code
# Real Estate Project - Backend

This is the backend component of a real estate project built using Spring Boot. The application follows a modular structure where each folder represents a distinct feature or entity within the project. Each folder typically contains an entity class, a controller for handling HTTP requests, and a repository for database operations.

## Project Structure

The project structure is organized into the following folders:

- **appuser:** Contains the entity, controller, and repository related to user management and authentication.
- **auth/security:** Handles authentication and security configurations.
- **configuration:** Holds configuration classes for the application.
- **email:** Manages email-related functionality such as sending notifications.
- **exception_handler:** Provides centralized exception handling for the application.
- **message:** Handles messages and notifications within the application.
- **photos:** Manages photo storage and retrieval for property listings.
- **property:** Includes entities, controllers, and repositories related to properties such as houses, apartments, or land.
- **review:** Deals with user reviews and ratings for properties.
- **tour:** Handles property tours and scheduling.

Additionally, you'll find the main application class `JwtAuthServiceApp.java` at the root of the project, which serves as the entry point for the Spring Boot application.

## Usage

To run the backend server locally, follow these steps:

1. **Clone the Repository:**
   ```bash
   git clone <repository-url>
2. **Navigate to the Project Directory:**
   ```bash
   cd real-estate-backend
3. **Build the Project:**
   ```bash
   mvn clean install
4. **Run the Application:**
   ```bash
   mvn spring-boot:run

## Swagger Configuration

This application utilizes Swagger for API documentation and testing. The Swagger configuration is defined in the `SwaggerConfig` class.

### Configuration Details

The `SwaggerConfig` class is annotated with `@Configuration` and `@EnableSwagger2`, enabling Swagger support for the application.

### API Documentation

Swagger is configured to generate API documentation based on the controllers and endpoints in the application. The `api()` method in the `SwaggerConfig` class creates a `Docket` bean that configures Swagger settings such as API selectors, API information, security schemes, and context.

### Security Configuration

Security is integrated with Swagger to handle authorization. The configuration includes defining a security context, security schemes, and setting up security references for API endpoints.

### Additional Features

- The `metadata()` method configures metadata for the API documentation, including title, description, version, license information, and contact details.
- The `apiKey()` method sets up an API key for authorization.
- The `discoverers()` method configures link discoverers for the API.

### Usage

Once the application is running, you can access the Swagger UI to explore and test the APIs. Typically, the Swagger UI is available at `http://localhost:8080/swagger-ui.html`.
