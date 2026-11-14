# [DEV-01] Implement Read, Update and Delete methods for users

**Ticket:** DEV-01 - Implement Read, Update and Delete methods for users

## Summary
Adds get-by-id, update name and delete endpoints for users, completing CRUD on the `User` resource.

## Changes
- **UserService**
  - `getUser(id)` – returns the user, throws `ResponseStatusException(NOT_FOUND)` if no user matches.
  - `updateUser(id, newUser)` – looks up the existing user (404 if missing), rejects the request with `400 BAD_REQUEST` if the body tries to change the id, otherwise copies the new name onto the existing user and saves.
  - `deleteUser(id)` – deletes the user by id and returns the deleted id.
  - Removed the `//TODO: DEV-XX` stubs.
- **UserController**
  - `GET /api/users/{id}`
  - `PUT /api/users/{id}`
  - `DELETE /api/users/{id}`
- **UserRepository** – no changes needed, the built-in `MongoRepository` methods cover it.

## Tests
Added `UserServiceTests` (JUnit 5 + Mockito) covering:
- create user
- get user / get user not found
- update user name / update user not found
- delete user

## Notes
Couldn't run the Gradle build locally as there's no JDK on my machine at the moment - please let CI confirm `./gradlew test` passes.
