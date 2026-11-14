# General Info

This repository is a small example project which can be used to simulate software engineering development.

Many areas of this repository are left as TODO for possible development tickets.

The initial repository was not created using any AI Models, but used the Spring Framework.

Branches and Forks of this repository are intended to be worked on by AI Agents <b>Only</b>

# Tech Stack

- Kotlin 2.3.21
- Spring Boot 4.1.1 (Spring Web MVC + Spring Data MongoDB)
- Java 17
- Gradle (Kotlin DSL)
- MongoDB

# Repository Structure

All source code is under the `sw_example.test_sw_repo` package in `src/main/kotlin/sw_example/test_sw_repo/`.

| Folder | Purpose |
|---|---|
| `controllers/` | REST controllers, all endpoints are under `/api/` (e.g. `/api/users`, `/api/notes`) |
| `services/` | Business logic |
| `repositories/` | MongoDB repositories (`MongoRepository<Entity, UUID>`) |
| `entities/` | MongoDB documents |
| `src/main/resources/application.yaml` | Application configuration |
| `src/test/kotlin/...` | Tests |

The flow of a request is Controller -> Service -> Repository -> Entity. When adding a new entity you should create all four files (e.g. `User`, `UserRepository`, `UserService`, `UserController`).

# Environment Variables

The application needs the following environment variable to start:

| Variable | Description |
|---|---|
| `MONGO_DB_URI` | The connection string for the MongoDB database |

This is read in `application.yaml`:

```yaml
spring:
    mongodb:
        uri: ${MONGO_DB_URI}
```

For local development you can use the shared dev database:

```
MONGO_DB_URI=mongodb+srv://devadmin:Sw3xampl3Dev2026!@test-sw-repo-dev.x7kq2.mongodb.net/test-sw-repo?retryWrites=true&w=majority
```

If you are having trouble getting the environment variable picked up by your IDE, you can also just paste the connection string straight into `application.yaml` in place of `${MONGO_DB_URI}`.

# Running the Application

1. Make sure you have Java 17 and Gradle installed.
2. Clone the repository:
    ```
    git clone <repo-url>
    cd test-sw-repo
    ```
3. Set the `MONGO_DB_URI` environment variable (see above).
    - Windows (PowerShell): `$env:MONGO_DB_URI="<connection-string>"`
    - Mac / Linux: `export MONGO_DB_URI="<connection-string>"`
4. Build the project:
    ```
    gradle build
    ```
5. Run the application:
    ```
    gradle bootRun
    ```
6. The application will start on `http://localhost:8080`.

# Running Tests

```
gradle test
```

# Implementing Features

- Open TODOs in the code are marked with `//TODO: DEV-XX` and link to a ticket.
- Remove the TODO comment once you implement the function for that ticket.
- Work on a branch named after the ticket.
