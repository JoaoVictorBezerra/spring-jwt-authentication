# Spring JWT Authentication Project 👾

This project is a template for implementing JSON Web Token (JWT) based authentication in a Spring application. JWT is a stateless authentication mechanism commonly used for securing RESTful APIs.

### Features 📈

- **JWT Token Authentication**: Uses JWT tokens for authenticating users.
- **User Registration**: Allows users to register for an account.
- **User Authentication**: Provides endpoints for user login.
- **Secured Endpoints**: Demonstrates how to secure endpoints based on user roles.

### Requirements 📄

- Java Development Kit (JDK) 8 or later
- Apache Maven
- MySQL (or any other preferred database)

### Installation ⚙️

1. Clone the repository:

```bash
    git clone https://github.com/joaovictorbezerra/spring-jwt-authentication.git
```

2. Navigate to the project directory:

```bash
    cd spring-jwt-authentication
```

3. Build the project using Maven:

```bash
    mvn clean install
```

4. Configure the application properties in `src/main/resources/application.properties`:

```properties
    # Database Configuration
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

5. Run the application:

```bash
    mvn spring-boot:run
```

### Usage 💻

Once the application is running, you can access the following endpoints:

- **User Registration**: `POST /api/auth/signup`
- **User Login**: `POST /api/auth/signin`
- **Secured Endpoint**: `GET /api/auth/test/user` (Requires authentication)
- **Admin Endpoint**: `GET /api/auth/test/organizer` (Requires authentication with organizer role)
- **Admin Endpoint**: `GET /api/auth/test/admin` (Requires authentication with admin role)

Make sure to include the JWT token obtained after login in the Authorization header of your requests.

### Contributing 🕹️

Contributions are welcome! If you find any issues or have suggestions for improvements, please feel free to open an issue or create a pull request.