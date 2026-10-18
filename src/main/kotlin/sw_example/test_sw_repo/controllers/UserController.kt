package sw_example.test_sw_repo.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sw_example.test_sw_repo.entities.User
import sw_example.test_sw_repo.services.UserService
import java.util.UUID

@RestController()
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping()
    fun createUser(
        @RequestBody user : User
    ) : ResponseEntity<User> {
        val response = userService.createUser(user)
        return ResponseEntity.ok(response);
    }

    /**
     * Gets a single user by their id
     */
    @GetMapping("/{id}")
    fun getUser(
        @PathVariable id : UUID
    ) : ResponseEntity<User> {
        val response = userService.getUser(id)
        return ResponseEntity.ok(response);
    }

    /**
     * Updates the name of a user. The id in the body must match the id in the path
     */
    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id : UUID,
        @RequestBody user : User
    ) : ResponseEntity<User> {
        val response = userService.updateUser(id, user)
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a user
     */
    @DeleteMapping("/{id}")
    fun deleteUser(
        @PathVariable id : UUID
    ) : ResponseEntity<Void> {
        userService.deleteUser(id)
        return ResponseEntity.noContent().build();
    }
}