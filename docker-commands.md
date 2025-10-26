# Docker Commands for Task Management System

## Build and Run

### 1. Build the JAR file
```bash
mvn clean package -DskipTests
```

### 2. Build and start containers
```bash
docker-compose up --build
```

### 3. Run in background
```bash
docker-compose up -d --build
```

### 4. Stop containers
```bash
docker-compose down
```

### 5. Stop and remove volumes (WARNING: This will delete all data)
```bash
docker-compose down -v
```

## Development Commands

### View logs
```bash
docker-compose logs -f api
docker-compose logs -f mysql
```

### Access MySQL container
```bash
docker exec -it task-mysql mysql -u taskuser -p
```

### Rebuild only API
```bash
docker-compose up --build api
```

## API Endpoints
- Create Task: `POST http://localhost:8082/api/tasks/task/create`
- Delete Task: `DELETE http://localhost:8082/api/tasks/task/delete/{id}`
- Get Latest 5: `GET http://localhost:8082/api/tasks/task/get-latest-five/`

## Database Access
- Host: localhost
- Port: 3307
- Database: task_system
- Username: taskuser
- Password: taskpass
