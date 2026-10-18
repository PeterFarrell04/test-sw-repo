package sw_example.test_sw_repo.services

import org.springframework.stereotype.Service
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
        //TODO: DEV-XX
    }

    fun getUser(id : UUID) {
        //TODO: DEV-XX
    }

    fun updateUser(id : UUID, newUser : User) {
        //TODO: DEV-XX
    }
}