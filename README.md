# CRUDMovie

A Java console application for managing movies using a MySQL database. It supports basic CRUD operations (Create, Read, Update, Delete).

## 🔧 Technologies Used

- Java 17
- Maven
- MySQL
- IntelliJ IDEA

## 🚀 Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your_username/CRUDMovie.git
   cd CRUDMovie
   
2. Connect to your MySQL server and execute:
CREATE DATABASE crud_movie;
3. In your database connection class (e.g., DBConnection.java), set your own credentials:
String url = "jdbc:mysql://localhost:3306/crud_movie";
String user = "root";
String password = "your_password";
4. Build the project using Maven: 
mvn clean package
5. Run the application:
java -jar target/CRUDMovie-1.0-SNAPSHOT.jar

