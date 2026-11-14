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

    fun deleteUser(id : UUID) : UUID {
        userRepository.deleteById(id)
        return id
    }

    fun getUser(id : UUID) : User {
        val allUsers = userRepository.findAll()
        var foundUser : User? = null
        for (user in allUsers) {
            if (user.id == id) {
                foundUser = user
            }
        }
        if (foundUser == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        }
        return foundUser
    }

    fun updateUser(id : UUID, newUser : User) : User {
        val existingUser = getUser(id)
        if (newUser.id != existingUser.id) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot update user id")
        }
        val updatedUser = existingUser.copy(name = newUser.name)
        userRepository.save(updatedUser)
        return updatedUser
    }
}