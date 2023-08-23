# Spring Boot Authentication with JWT

## Overview

This Java Spring Boot project demonstrates how to implement authentication and authorization using JSON Web Tokens (JWT). JWT is a secure and stateless way to handle user authentication, making it ideal for building modern web applications.

## Features

- User registration and authentication
- JWT-based token generation and validation
- Role-based authorization
- Secure password hashing
- Swagger documentation for APIs

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 8 or higher installed
- Maven installed
- Your favorite IDE (e.g., IntelliJ IDEA, Eclipse)
- Basic understanding of Spring Boot

## Getting Started

1. Clone the repository:

   ```shell
   git clone https://github.com/OriAboudi/Spirng-Authentication.git

# Configuration

You can configure the JWT secret key and expiration time in the `src/main/resources/application.yml` file.

# Usage

## User Registration

To register a new user, make a POST request to `/api/V1/auth/register` with the following JSON payload:

  ``json
  {
      "username": "your-username",
      "email": "your-email@example.com",
      "password": "your-password"
  }``


# User Login

To obtain a JWT token for authentication, follow these steps:

1. **Make a POST Request**

   Send a POST request to `/api/V1/auth/authenticate` with the following JSON payload:

   ```json
   {
       "usernameOrEmail": "your-username-or-email",
       "password": "your-password"
   }


License

This project is licensed under the MIT License. See the LICENSE file for details.


Contact

If you have any questions or need further assistance, please contact oriaboudi2001@gmail.com
