# HMBM-Diet-Manager

The porpouse of this project is to create an Diet manager MVP(mainly for educational purposes, this is important to say), this particullar repository concearns only to the backend of the project, since backend and frontend will be separated between two different repositories.

# How do i run this project?

This guide explains how to set up and run the HMBM-Diet-Manager project.

1. Required Technologies
Before you begin, ensure you have the following installed:

Java Development Kit (JDK): Version 17 or higher is generally recommended for modern Spring Boot applications. Please check your project's pom.xml (if using Maven) or build.gradle (if using Gradle) for the specific Java version defined (usually under a <java.version> or sourceCompatibility property).
Apache Maven or Gradle: This project is a Spring Boot application, which typically uses Maven (look for a pom.xml file in the project root c:\HMBM-Diet-Manager) or Gradle (look for build.gradle). Install the respective build tool.
Database Server: The project uses Spring Data JPA, indicating a need for a relational database. You'll need to choose and install one. Common choices include:
MySQL
PostgreSQL
H2 (often used for development/testing due to its embedded nature)
SQL Server
Oracle You will configure the connection details in the application.properties file.
IDE (Optional but Recommended): An Integrated Development Environment like IntelliJ IDEA, Eclipse, or VS Code (with Java and Spring Boot extensions) can simplify development and running the application.
Key Technologies Used in This Project:

Spring Boot: The core framework used for building the application.
Spring Security: Handles authentication and authorization, as seen in files like SecurityConfiguration.java and JwtAuthenticationFilter.java.
Spring Data JPA: Used for database interaction, with entities like UserEntity and repositories like UserRepository.
Hibernate: The default JPA implementation used by Spring Boot.
JWT (JSON Web Tokens): Used for stateless authentication, managed by JwtService.
Lombok: A library to reduce boilerplate Java code (e.g., getters, setters, constructors), evident from annotations like @Getter, @Setter, @Builder, @RequiredArgsConstructor in various classes.
2. Configuration
You'll need to configure environment variables (for JWT secret) and application properties (for database connection, JWT settings, etc.).

2.1. Environment Variables (.env file)
The project includes a utility, JwtSecretGenerator.java, which suggests the use of a .env file for storing the JWT_SECRET. This file is expected to be at the project root: C:\HMBM-Diet-Manager\.env.

The JwtService uses @Value("${jwt.secret}") and @Value("${jwt.expirationMS}"). These properties need to be available to the Spring application.

Steps to generate/update JWT_SECRET using JwtSecretGenerator.java:

Locate the utility: The file is at c:\HMBM-Diet-Manager\src\main\java\com\M\N0\HMBM\Diet\utils\JwtSecretGenerator.java.
Run JwtSecretGenerator.java:
You can run this Java class directly from your IDE.
Alternatively, compile and run it from the command line:
bash
Run
cd c:\HMBM-Diet-Manager\src\main\java\com\M\N0\HMBM\Diet\utilsjavac JwtSecretGenerator.javajava com.M.N0.HMBM.Diet.utils.JwtSecretGenerator
This will create or update the C:\HMBM-Diet-Manager\.env file with a line like:
plaintext

JWT_SECRET=your_generated_secure_random_key_here
Take note of this generated key.
Important Note on Spring and .env files: Spring Boot doesn't natively load .env files into its Environment for @Value resolution by default. The JwtSecretGenerator writes to .env, but for Spring's JwtService to pick up jwt.secret and jwt.expirationMS, you have a few options:

Explicitly set them in application.properties (recommended for clarity, see next section). You would copy the value from the .env file.
Use a library like io.github.cdimascio:dotenv-java and configure it to load .env variables into system properties before Spring starts.
Set them as actual system environment variables.
For simplicity, this guide will assume you'll set them in application.properties.

2.2. Application Properties (src/main/resources/application.properties)
Create or update the application.properties file located in the src/main/resources directory. If this directory or file doesn't exist, you'll need to create them.

This file is crucial for Spring Boot configuration.

Example application.properties:

properties

# Server Configurationserver.port=8080 # Default port is 8080, change if needed# Database Configuration (Choose one section and adapt to your database)# Example for MySQL:# spring.datasource.url=jdbc:mysql://localhost:3306/hmbm_diet_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true# spring.datasource.username=your_mysql_user# spring.datasource.password=your_mysql_password# spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver# spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect# Example for PostgreSQL:# spring.datasource.url=jdbc:postgresql://localhost:5432/hmbm_diet_db# spring.datasource.username=your_postgres_user# spring.datasource.password=your_postgres_password# spring.datasource.driver-class-name=org.postgresql.Driver# spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect# Example for H2 (In-Memory Database - good for quick testing, data lost on shutdown):spring.datasource.url=jdbc:h2:mem:hmbmdietdbspring.datasource.username=saspring.datasource.password=passwordspring.datasource.driver-class-name=org.h2.Driverspring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialectspring.h2.console.enabled=true # To access H2 console at /h2-consolespring.h2.console.path=/h2-console# JPA/Hibernate Configurationspring.jpa.hibernate.ddl-auto=update # Options: none, validate, update, create, create-drop                                     #                                      'update                                     ' is                                      conveni                                     ent                                      for                                      develop                                     ment.                                     #                                      'create                                     ' or                                      'create                                     -drop'                                      will                                      recreat                                     e                                      tables                                      (data                                      lost).                                     #                                      'valida                                     te'                                      checks                                      schema                                      against                                                                           entitie                                     s.                                     #                                      'none'                                      does                                      nothing                                      to                                      the                                      schema.spring.jpa.show-sql=true             # Set to 'false' in production for cleaner logs.# JWT Configuration# Copy the secret generated by JwtSecretGenerator.java into jwt.secretjwt.secret=your_generated_secure_random_key_here_from_step_2.1jwt.expirationMS=86400000 # Token expiration time in milliseconds (e.g., 24 hours = 24 * 60 * 60 * 1000)                          # This value is                           used by                           JwtService's                           @Value("${jwt.                          expirationMS}")# Enable JPA Auditing (already handled by @EnableJpaAuditing in HmbmDietManagerApplication.java)# No specific property needed here if @EnableJpaAuditing is present.
Explanation of Key Properties:

server.port: The network port on which the application will listen.
spring.datasource.url: The JDBC connection URL for your database.
Replace placeholders like hmbm_diet_db, localhost, port numbers, usernames, and passwords with your actual database details.
spring.datasource.username: The username for your database connection.
spring.datasource.password: The password for your database connection.
spring.datasource.driver-class-name: The fully qualified name of the JDBC driver for your database.
spring.jpa.properties.hibernate.dialect: Helps Hibernate generate SQL optimized for your specific database.
spring.jpa.hibernate.ddl-auto: Controls Hibernate's schema generation strategy. Use update or validate for development against an existing schema, or create/create-drop if you want Hibernate to manage schema creation (be careful, as this can lead to data loss).
spring.jpa.show-sql: If true, Hibernate will log all executed SQL statements to the console.
jwt.secret: CRITICAL. This is the secret key used to sign and verify JWTs. It must be the same key generated by JwtSecretGenerator.java (or any other secure key you decide to use).
jwt.expirationMS: The duration for which JWTs will be valid, in milliseconds. The JwtService uses this value.
3. Database Setup
Install your chosen database server (e.g., MySQL, PostgreSQL) if you are not using H2 in-memory.
If not using H2 or ddl-auto=create, create a new database (e.g., hmbm_diet_db or the name you specified in spring.datasource.url).
If applicable, create a database user with the necessary permissions (connect, DML, DDL if Hibernate is managing the schema) for the database, matching the credentials in application.properties.
Ensure your database server is running and accessible from the machine where you'll run the Spring Boot application.
4. Build and Run the Project
Using Apache Maven (if your project has a pom.xml file)
Open a terminal or command prompt.
Navigate to the project root directory: cd c:\HMBM-Diet-Manager
Clean and build the project (this will download dependencies and compile the code):
bash
Run
mvn clean install
Run the application:
bash
Run
mvn spring-boot:run
Alternatively, after a successful build, you can run the packaged JAR file (typically found in the target directory):
bash
Run
java -jar target/your-application-name.jar
(Replace your-application-name.jar with the actual name of the JAR file, e.g., HMBM-Diet-Manager-0.0.1-SNAPSHOT.jar).
Using Gradle (if your project has a build.gradle file)
Open a terminal or command prompt.
Navigate to the project root directory: cd c:\HMBM-Diet-Manager
Clean and build the project:
bash
Run
./gradlew clean build
(On Windows, you might use gradlew.bat clean build).
Run the application:
bash
Run
./gradlew bootRun
(On Windows: gradlew.bat bootRun).
Using an IDE (IntelliJ IDEA, Eclipse, VS Code)
Import the project into your IDE (usually by opening the pom.xml or build.gradle file).
Ensure your configurations from step 2 (.env content reflected in application.properties) are correctly set up.
Locate the main application class: HmbmDietManagerApplication.
Right-click on this file in your IDE and select "Run" or "Debug". The IDE will handle the build and execution.
5. Accessing the Application
Once the application is running (you should see Spring Boot startup logs, often ending with a line indicating the application has started on a specific port), you can interact with it:

API Endpoints: The application exposes REST APIs. The base URL will typically be http://localhost:YOUR_PORT (e.g., http://localhost:8080 if you used the default port).
Authentication Endpoints: Located under /auth (e.g., /auth/signup, /auth/login), as defined in AuthenticationController. These are generally accessible without prior authentication, as per your SecurityConfiguration.java (.requestMatchers("/auth/**").permitAll()).
Protected Endpoints: Other endpoints, like /users/me (from UserController), will require a valid JWT Bearer token in the Authorization header of your HTTP requests.
CORS: Your SecurityConfiguration.java is configured to allow requests from http://localhost:8005. If your frontend application (if any) is running on a different origin (domain or port), you'll need to update the setAllowedOrigins list in the corsConfigurationSource bean within SecurityConfiguration.java.
Troubleshooting Tips
Port Conflict: If you see an error like "Port XXXX already in use," change the server.port in application.properties to an available port.
Database Connection Issues:
Verify all spring.datasource.* properties in application.properties.
Ensure your database server is running and accessible.
Check database user credentials and permissions.
Look for detailed error messages in the application logs and your database server logs.
JWT Errors / Authentication Failures:
Double-check that the jwt.secret in application.properties is exactly the one you intend to use (e.g., from the .env file generated by JwtSecretGenerator.java).
Ensure tokens are not expired.
Dependency Issues (ClassNotFoundException, etc.):
Run a clean build (mvn clean install or ./gradlew clean build --refresh-dependencies).
Ensure your IDE has correctly imported the project and resolved dependencies.
Check Application Logs: The console output from Spring Boot will contain valuable information, including error messages and stack traces, which can help diagnose problems.
This guide should provide a solid foundation for getting the HMBM-Diet-Manager project up and running. Good luck!
