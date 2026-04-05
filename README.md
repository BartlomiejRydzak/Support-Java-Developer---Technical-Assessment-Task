# Demo — Product & Producer REST API

A Spring Boot REST API for managing **Producers** and **Products** with JSONB attribute support, backed by PostgreSQL and schema-managed by Liquibase.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.5.13 |
| Persistence | Spring Data JPA + Hibernate |
| Database | PostgreSQL |
| Schema migrations | Liquibase |
| JSON column support | Hypersistence Utils 3.7.6 |
| Validation | Jakarta Bean Validation |
| Build tool | Maven |

---

## Configuration

The application reads database credentials from **environment variables**:

| Variable | Description | Example |
|---|---|---|
| `DB_URL` | JDBC connection URL | `databse_url` |
| `DB_USERNAME` | Database username | `your_username` |
| `DB_PASSWORD` | Database password | `your_password` |

---

## Building & Running

### Run in development mode

```bash
mvn spring-boot:run
```

### Build a JAR and run it

```bash
mvn clean package -DskipTests
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

The server starts on **`http://localhost:8080`** by default.

---

## API Reference

### Producers

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/producers` | List all producers |
| `POST` | `/api/producers` | Create a new producer |
| `PUT` | `/api/producers` | Update an existing producer |
| `DELETE` | `/api/producers/{id}` | Delete a producer by ID |

**Create producer — request body:**
```json
{
  "name": "Company"
}
```

---

### Products

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/products` | List all products |
| `POST` | `/api/products` | Create a new product |
| `PUT` | `/api/products` | Update an existing product |
| `DELETE` | `/api/products/{id}` | Delete a product by ID |
| `POST` | `/api/products/search` | Search products by filters |

**Create product — request body:**
```json
{
  "name": "TV",
  "producerId": 1,
  "attributes": {
    "color": "black",
    "weight": 1.5
  }
}
```

**Search products — request body** *(all fields optional)*:
```json
{
  "name": "TV",
  "producerId": 1,
  "attributes": { "color": "black" }
}
```

Search supports:
- **Partial, case-insensitive** name matching
- **Producer ID** filtering
- **JSONB containment** filtering on `attributes` (returns products whose attributes contain all provided key-value pairs)

---

## Database Schema

Managed automatically by Liquibase (`db/changelog/db.changelog-master.yaml`).

```
producer
├── id          BIGINT PRIMARY KEY (auto-increment)
└── name        VARCHAR(255) NOT NULL

product
├── id          BIGINT PRIMARY KEY (auto-increment)
├── name        VARCHAR(255) NOT NULL
├── producer_id BIGINT NOT NULL → producer(id)
└── attributes  JSONB
```

---

## Project Structure

```
src/main/java/com/example/demo/
├── DemoApplication.java
├── controller/
│   ├── ProducerController.java
│   └── ProductController.java
├── service/
│   ├── ProducerService.java
│   ├── ProducerServiceImpl.java
│   ├── ProductService.java
│   └── ProductServiceImpl.java
├── repository/
│   ├── ProducerRepository.java
│   └── ProductRepository.java
├── entity/
│   ├── Producer.java
│   └── Product.java
└── dto/
    ├── ProductRequest.java
    └── ProductSearchRequest.java

src/main/resources/
├── application.properties
└── db/changelog/
    └── db.changelog-master.yaml
```
