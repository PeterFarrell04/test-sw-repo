# Repository Rules

Coding standards deduced from the existing code in this repository. All paths are relative to the repository root.

## 1. Project & Tech Stack

1. The project is a Kotlin Spring Boot (Spring Web MVC) application built with Gradle (Kotlin DSL — `build.gradle.kts`, `settings.gradle.kts`).
2. Target Java toolchain 21. Dependencies are declared in `build.gradle.kts`; add new ones in the existing `dependencies { }` block using the `implementation(...)` / `testImplementation(...)` form.
3. Application configuration goes in `src/main/resources/application.properties` (properties format, not YAML).
4. Use the Gradle wrapper (`./gradlew`, `gradlew.bat`) rather than a system Gradle install.

## 2. Architecture & File Locations

The base package is `sw_example.test_sw_repo`, located at `src/main/kotlin/sw_example/test_sw_repo/`. Code is layered by type into sub-packages:

| Layer | Package | Location for new files | Naming |
|---|---|---|---|
| Controllers | `sw_example.test_sw_repo.controllers` | `src/main/kotlin/sw_example/test_sw_repo/controllers/` | `<Entity>Controller.kt` |
| Services | `sw_example.test_sw_repo.services` | `src/main/kotlin/sw_example/test_sw_repo/services/` | `<Entity>Service.kt` |
| Entities / models | `sw_example.test_sw_repo.entities` | `src/main/kotlin/sw_example/test_sw_repo/entities/` | `<Entity>.kt` (singular noun) |
| Application entry point | `sw_example.test_sw_repo` | `src/main/kotlin/sw_example/test_sw_repo/TestSwRepoApplication.kt` | — (do not add other classes here) |
| Tests | mirror the main package | `src/test/kotlin/sw_example/test_sw_repo/...` | `<ClassUnderTest>Tests.kt` |

5. One top-level class per file; the file name matches the class name (PascalCase).
6. Package names use lowercase with underscores (`sw_example.test_sw_repo`), and the directory structure must match the package declaration.
7. Any new layer (e.g. repositories, DTOs, config) should follow the same pattern: a new lowercase plural sub-package directly under `src/main/kotlin/sw_example/test_sw_repo/`.
8. Each domain entity gets a matching controller and service (e.g. `User` → `UserController` + `UserService`).

### Controllers

9. Annotate with `@RestController` and a class-level `@RequestMapping("/api/<plural-resource>")` (e.g. `/api/users`, `/api/notes`). All endpoints live under `/api/`, with plural, lowercase resource names.
10. Controllers are thin: they delegate to a service and contain no business logic.
11. Inject dependencies via the primary constructor as `private val` properties (constructor injection; no `@Autowired` field injection):
    ```kotlin
    class UserController(
        private val userService: UserService
    ) {
    ```
12. Use HTTP-method mapping annotations (`@PostMapping`, etc.) on methods and return `ResponseEntity<T>` (e.g. `ResponseEntity.ok(response)`).
13. Request bodies are bound with `@RequestBody` and typed as the entity class.

### Services

14. Annotate with `@Service`. Services contain the business logic and are called by controllers.
15. Method names are verb + entity in camelCase: `createUser`, `getUser`, `updateUser`, `deleteUser`, `getNotes` (plural noun when returning a collection).
16. Identifiers are passed as `id : UUID`.

### Entities

17. Entities are Kotlin `data class`es with immutable `val` properties declared in the primary constructor, one per line.
18. Every entity has an `id : UUID` property listed first (`java.util.UUID`). Foreign references use `<entity>Id : UUID` (e.g. `userId`).

## 3. Formatting

19. **Indentation:** In hand-written source files (controllers, services, entities), indent with **4 spaces**, not tabs. (Spring Initializr–generated files — `build.gradle.kts`, `TestSwRepoApplication.kt`, `TestSwRepoApplicationTests.kt` — use tabs; keep tabs when editing those files so each file stays internally consistent.)
20. **Braces:** Opening brace `{` goes on the same line as the declaration, preceded by a single space (`class NoteService {`, `fun getNotes(): List<Note> {`). Closing brace `}` goes on its own line, aligned with the start of the declaration.
21. Leave one blank line after the opening brace of a class body, and one blank line between functions.
22. **Multi-line parameter lists:** constructor parameters for controllers and entities, and annotated function parameters, go one per line, indented one level, with the closing `)` on its own line at the declaration's indentation, followed by ` {` (or the return type):
    ```kotlin
    fun createUser(
        @RequestBody user : User
    ) : ResponseEntity<User> {
    ```
23. **Type annotations:** the dominant style in this codebase puts a space on both sides of the colon for parameters and properties: `id : UUID`, `user : User`. For function return types in services, no space before the colon: `fun getNotes(): List<Note>`.
24. Do not use trailing commas in parameter lists.
25. Do not end statements with semicolons (`;`).
26. **Imports:** one import per line, no wildcard imports. Order: framework/third-party (`org.springframework...`) first, then project imports (`sw_example.test_sw_repo...`), then `java.*` last, each group alphabetical, with no blank lines between groups. Leave one blank line after `package` and after the import block.
27. Annotations go on their own line above the class/function they apply to.
28. Hand-written files use CRLF line endings and no trailing newline at end of file (matching the current controllers/services/entities); `gradlew` must remain LF and `*.bat` CRLF, as enforced by `.gitattributes`.

## 4. Comments

29. Comments are sparse; code is expected to be self-explanatory. No KDoc/Javadoc blocks are used.
30. Unimplemented functionality is marked with a single-line TODO comment referencing a ticket ID, with no space after `//`:
    ```kotlin
    //TODO: DEV-XX
    ```
    Replace `XX` with the actual ticket number.
31. Stub functions keep their full signature and contain only the TODO comment (no `TODO()` call or thrown exception).

## 5. Naming

32. Classes: PascalCase nouns (`User`, `NoteService`).
33. Functions and properties: camelCase (`createUser`, `noteService`, `userId`).
34. Injected dependency properties are named after their type in camelCase (`UserService` → `userService`).
35. Local variable holding a service result in a controller is named `response`.

## 6. Testing

36. Tests use JUnit 5 (`org.junit.jupiter.api.Test`) with `kotlin-test-junit5`; Spring context tests use `@SpringBootTest`.
37. Test classes live under `src/test/kotlin/` in the same package as the class under test and are named with a `Tests` suffix.

## 7. Version Control

38. IDE and build output (`.idea`, `build/`, `.gradle`, `.kotlin`, `.vscode/`, etc.) must not be committed — see `.gitignore`.
39. Ticket IDs follow the `DEV-<number>` format.
