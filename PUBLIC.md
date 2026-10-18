# DEV-00: Add get, update and delete endpoints for users

**Ticket:** DEV-00 - Implement Read, Update and Delete methods for users
**Branch:** `feature/DEV-00-user-crud`

## Summary

Before this, users could only be created. This PR adds the rest of the CRUD functionality for users so we can get a user by id, update a user's name and delete a user.

## Changes

### `UserService`
- Implemented `getUser(id)`. It looks the user up and throws a `ResponseStatusException` with `404 NOT_FOUND` if no user has that id.
- Implemented `updateUser(id, newUser)`. If the id in the request body doesn't match the id in the path, it throws a `400 BAD_REQUEST`, so the user's id can't be changed. If the user doesn't exist it returns a 404 (it reuses `getUser`). Only the `name` is copied onto the existing user before saving.
- Implemented `deleteUser(id)`, which removes the user from the repository.

### `UserController`
- `GET /api/users/{id}` returns the user (200), or 404.
- `PUT /api/users/{id}` updates the user's name and returns the updated user (200). Returns 400 if the body has a different id, or 404.
- `DELETE /api/users/{id}` deletes the user and returns 204 No Content.
- Added some short doc comments on the new endpoints.

### `application.yaml`
- Added `server.error` config so the error responses include the details of what went wrong (e.g. the "User not found" message). Without this Spring hides the reason, which makes it hard to tell what happened from the client.

### Tests
- Added `UserServiceTests` (under `src/test/kotlin/sw_example/test_sw_repo/services/`) using Mockito to mock the `UserRepository`. Covers:
  - creating a user
  - getting a user + 404 when not found
  - updating a name, 400 when id is changed, 404 when not found
  - deleting a user

No changes were needed to `UserRepository`, the built-in `MongoRepository` methods were enough.

## How to test

```
./gradlew test --tests '*UserServiceTests'
```

All 7 tests pass locally.

Manually (with `MONGO_DB_URI` set):
1. `POST /api/users` with `{ "name": "Peter" }` and copy the returned id
2. `GET /api/users/{id}` returns the user
3. `PUT /api/users/{id}` with `{ "id": "{id}", "name": "John" }` returns the updated user
4. `PUT` with a different id in the body returns 400
5. `DELETE /api/users/{id}` returns 204, then `GET` returns 404

## Notes
- For updates, the body needs to include the user's id as well as the name since the `User` entity generates a random id if one isn't passed.
