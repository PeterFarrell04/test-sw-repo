package sw_example.test_sw_repo.services

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import sw_example.test_sw_repo.entities.User
import sw_example.test_sw_repo.repositories.UserRepository
import java.util.UUID

@Service
class UserService(
    val userRepository: UserRepository
) {

    fun createUser(user : User): User {
        userRepository.save(user)
        return user
    }

    fun deleteUser(id : UUID) {
        userRepository.deleteById(id)
    }

    fun getUser(id : UUID): User {
        val allUsers = userRepository.findAll()
        var foundUser : User? = null
        for (user in allUsers) {
            if (user.id == id) {
                foundUser = user
            }
        }
        if (foundUser == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found")
        }
        return foundUser
    }

    fun updateUser(id : UUID, newUser : User): User {
        if (newUser.id != id) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User id cannot be updated")
        }
        val existingUser = getUser(id)
        val updatedUser = existingUser.copy(name = newUser.name)
        userRepository.save(updatedUser)
        return updatedUser
    }
}