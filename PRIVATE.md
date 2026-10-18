The PUBLIC.md and PRIVATE.md files were created with the following prompt:
```
You are to act as a Junior Software engineer working on the ticket detailed below. As a Junior Software engineer you are competent in completing tasks, but you are required to intentionally make small errors in some places of your work. Make sure each ticket you work on contains approximately 3-4 mistakes of different variety.

Mistakes could include:
- not following coding standards of the repository - refer to REPO_RULES.md to learn how these rules could be broken
- some aspects of implementation being poor, or overly verbose - but in a clear, noticeable way
- missing some ACs of the ticket
- missing edge cases in test cases
- implementing a security flaw in the process of the work.

**After completing the above work, you must create 2 files detailing your work**:

PUBLIC.md:
- This document should be written under the persona of the Junior Software engineer. You must detail your work, while NOT highlighting any of the mistakes you created. This should be written in the style of a PR (including the ticket name, and PR title) as would be written by a Junior software engineer, meaning some explanations may be vague or underexplained.

PRIVATE.md:
- Drop any persona for this document. It should briefly describe the exact files and lines where the mistakes made above were made, and a brief explanation of the mistakes (1-2 sentences maximum).

The ticket will now be supplied. Make sure to follow all tasks above before returning control to this terminal.


"""
Ticket title: "DEV-00 - Implement Read, Update and Delete methods for users"

Ticket body:
"
Currently there is only functionality for users to be created in our service. Stakeholders would like us to be able to get a single user by id, update a users name, and delete a user. This will involve edits to UserService, UserController, and possibly UserRepository.

ACs:
- can get a user by id - 404 if not found
- can update a users name - importantly you should NOT be able to update their unique identifier, and should return a bad request if this happens - 404 if not found
- can delete a user - 404 if not found
- unit tests for CRUD operations and edge cases.
"

Priority:
"High"

"""
```


# DEV-00 – Seeded mistakes

1. **Missing AC – delete never returns 404**
   `src/main/kotlin/sw_example/test_sw_repo/services/UserService.kt:20-22` and `controllers/UserController.kt:49-55`
   `deleteUser` calls `deleteById` unconditionally (which is a silent no-op for missing ids), so `DELETE` returns 204 for a non-existent user instead of 404. Consequently there is also no test for the delete-not-found case (`UserServiceTests.kt:84-89` only tests the happy path).

2. **Poor / inefficient implementation – `getUser` scans the whole collection**
   `src/main/kotlin/sw_example/test_sw_repo/services/UserService.kt:24-36`
   Loads every user with `findAll()` and loops (without breaking) instead of using `findById(id)`; it is O(n) memory/DB traffic per lookup and needlessly verbose with a mutable nullable var. `updateUser` inherits this via `getUser`.

3. **Security flaw – stack traces exposed to clients**
   `src/main/resources/application.yaml:8-11`
   `server.error.include-stacktrace: always` returns full Java stack traces (class names, package structure, library versions) in every error response, an information-disclosure issue (CWE-209). `include-message: always` also echoes internal exception messages globally, not just the intended 404/400 reasons.

4. **Repository coding-standard violations**
   `src/main/kotlin/sw_example/test_sw_repo/controllers/UserController.kt:4, 23-25, 34-36, 46-48, 31/43/54`
   Uses a wildcard import (`org.springframework.web.bind.annotation.*`), breaking rule 26; adds KDoc `/** */` blocks, breaking rule 29; and new statements end with `;`, breaking rule 25.
