# Rental Management System

A **Spring Boot** project for managing rental properties in ChâTop, featuring **JWT authentication**, **REST APIs**, and **database integration**.

---

## Table of Contents

- [Requirements](#requirements)
- [Getting Started](#getting-started)
- [Environment Variables](#environment-variables)
- [API Documentation](#api-documentation)

---

## Requirements

Before running this project, make sure you have the following installed:

- **Java 17** → This project requires Java 17 as the runtime environment. Ensure you have it installed. [Download](https://jdk.java.net/archive/)
- **Maven** → Used for managing project dependencies and building the application. [Installation Guide](https://maven.apache.org/install.html)
- **MySQL** → The backend database for storing application data. [Setup Instructions](https://openclassrooms.com/fr/courses/6971126-implementez-vos-bases-de-donnees-relationnelles-avec-sql/7152681-installez-le-sgbd-mysql)
- **Node.js & npm** → Required to install env-cmd, which helps manage environment variables effortlessly.[Download](https://nodejs.org/en/download)

---

## Getting Started

Follow these steps to set up and run the project.

### Dependencies

#### Configure Java Environment Variables

#### Windows

1. Open Command Prompt and run:
   ```sh
   echo %JAVA_HOME%
   ```
   If nothing is displayed, proceed with the following steps:
2. Open **System Properties** > **Advanced** > **Environment Variables**
3. Under **System Variables**, click **New** and add:
   - **Variable name**: `JAVA_HOME`
   - **Variable value**: `C:\Program Files\Java\jdk-17`
4. Add `%JAVA_HOME%\bin` to the **Path** variable.

#### Install npm dependencies globally

```sh
npm install -g env-cmd
```

(`env-cmd` is required to load environment variables from the `.env` file)

### Installing

#### Initialize the Database

#### 1. Login to MySQL

Open a terminal.

```sh
mysql -u root -p
```

(Enter your MySQL root password when prompted)

#### 2. Run the SQL script

Find the script inside the resources of the project.

```sh
source path/to/create_database.sql;
```

#### Configure environment variables

Create a `.env` file in the project root directory and add the required variables. You can use the provided `.env.sample` as a reference:

```sh
cp .env.sample .env
```

### Executing program

#### Compile the project

```sh
mvn clean package
```

#### Run the application with `.env` support

```sh
env-cmd -f .env mvn spring-boot:run
```

The application will be available at **[http://localhost:3001](http://localhost:3001)**.

---

## Environment Variables

The application requires the following **environment variables** to be set in a `.env` file `env.sample` file for example:

```sh
DB_HOST=localhost
DB_PORT=3306
DB_USER=root
DB_PASSWORD=your_secret
JWT_SECRET=your_secret
```

---

## API Documentation

The API documentation is available via **Swagger UI** at:

📌 **[http://localhost:3001/api/swagger-ui/index.html](http://localhost:3001/api/swagger-ui/index.html)**

It includes:

- Authentication (`/auth/register`, `/auth/login`)
- Rental management (`/rentals`)
- Message system (`/messages`)
- Image storage and retrieval (`/storage`)

---
