# Instructions to Run Popcorn Palace Movie Ticket Booking System

## 1. Clone the Repository
```sh
git pull https://github.com/xgrm/Popcorn-Palace-Movie-Ticket-Booking-System.git
```

## 2. Navigate to the Project Directory
```sh
cd Popcorn-Palace-Movie-Ticket-Booking-System/
```

## 3. Create a Data Folder for PostgreSQL
```sh
mkdir data
```

## 4. Run the Docker Compose File
```sh
docker compose up -d
```

## 5. Build and Run the Spring Boot Project
If you have Maven installed on your system, use:
```sh
mvn clean install
mvn spring-boot:run
```
## 6. Run the Tests
Execute tests using Maven:
```sh
mvn test
```

