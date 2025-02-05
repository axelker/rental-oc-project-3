# 📌 Spring Boot Project

## 🚀 Requirements
Before running this project, make sure you have the following installed:

- **Java 17**
- **Maven**
- **Node.js & npm** (for `env-cmd`)

---

## ▶️ Run the project
Once all dependencies are installed, use the following commands to start the application:

1. **Clone the project** (if necessary):
   ```sh
   git clone https://github.com/axelker/rental-oc-project-3.git
   ```

2. **Install npm dependencies globably** (to load environment variables):
   ```sh
   npm install -g env-cmd
   ```

3. **Define .env file**:
Create an .env file with the necessary variables (examples with .env.sample)

4. **Compile the project**:
   ```sh
   mvn clean package
   ```

5. **Run the application with `.env` support**:
   ```sh
   env-cmd -f .env mvn spring-boot:run
   ```

The application will be available at: **http://localhost:3001**

---

