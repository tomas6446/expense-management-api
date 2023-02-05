# SpringBoot Expense Management API

A SpringBoot backend application that provides the necessary APIs to manage expenses for [impensa](https://github.com/richard96292/impensa)


## Prerequisites
- Java 8 or above
- Maven

## Run
1. Clone the repository:
   `git clone https://github.com/tomas6446/expense-management-app`
2. Navigate to the project directory
3. Setup database using database.sql file instructions
4. Build the app with: `mvn clean install` and 'mvn build'
5. Run the app with: `mvn spring-boot:run`

## Testing
The project uses Diffblue AI for writing tests. To run tests, use the following command: `mvn test`

## Key Freatures
- User authentication using SpringBoot Security
- Password encoding using Bcrypt
- Spring Security configuration
- Custom security filters
