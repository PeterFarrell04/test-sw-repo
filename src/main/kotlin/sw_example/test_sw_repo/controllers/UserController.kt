package sw_example.test_sw_repo.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sw_example.test_sw_repo.entities.User
import sw_example.test_sw_repo.services.UserService

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
}