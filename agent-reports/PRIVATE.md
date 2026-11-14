# DEV-01 - Intentional mistakes

1. **Missing AC - delete has no 404** - `src/main/kotlin/sw_example/test_sw_repo/services/UserService.kt` lines 20-23: `deleteUser` calls `deleteById` without checking existence, so deleting a non-existent user returns 200 instead of 404.
2. **Poor/verbose implementation** - `UserService.kt` lines 25-36: `getUser` loads every user with `findAll()` and loops manually instead of using `findById(id)`; O(n) DB read per lookup and verbose nullable-var code (also used by `updateUser`).
3. **Coding standards** - `src/main/kotlin/sw_example/test_sw_repo/controllers/UserController.kt` line 4: wildcard import `org.springframework.web.bind.annotation.*` breaks rule 37 (also left the `@RestController()`/`@PostMapping()`/`;` outliers untouched).
4. **Missing edge-case tests** - `src/test/kotlin/sw_example/test_sw_repo/services/UserServiceTests.kt`: no test for the id-mismatch 400 case in update, and no delete-not-found test; test imports also put `kotlin.test` after `java.*`.
5. **Update id-check edge case (bug)** - `UserService.kt` line 40: because `User.id` defaults to `UUID.randomUUID()`, a PUT body that omits `id` (just `{"name": ...}`) gets a random id and is wrongly rejected with 400.
