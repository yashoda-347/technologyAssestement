# Task Management System

Spring Boot REST API with MySQL database, containerized with Docker.

## Prerequisites

- Java 17
- Maven 3.6+
- Docker & Docker Compose

## How to Run

### 1. Clone and Build
```bash
git clone https://github.com/yourusername/task-management-system.git
cd task-management-system
mvn clean package -DskipTests
```

### 2. Start with Docker
```bash
docker-compose up --build
```

### 3. Access
- **API**: http://localhost:8082
- **Database**: localhost:3307

## API Endpoints

- **POST** `/api/tasks/task/create` - Create task
- **DELETE** `/api/tasks/task/delete/{id}` - Delete task  
- **GET** `/api/tasks/task/get-latest-five/` - Get latest 5 tasks

## Example Usage

```bash
# Create task
curl -X POST http://localhost:8082/api/tasks/task/create \
  -H "Content-Type: application/json" \
  -d '{"title": "My Task", "description": "Task description"}'

# Get tasks
curl http://localhost:8082/api/tasks/task/get-latest-five/
```

## Docker Commands

```bash
docker-compose up -d          # Start
docker-compose down           # Stop
docker-compose logs -f        # View logs
```

## Database

- **Host**: localhost:3307
- **Database**: task_system
- **Username**: taskuser
- **Password**: taskpass
