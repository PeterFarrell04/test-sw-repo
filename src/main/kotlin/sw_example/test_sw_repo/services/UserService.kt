package sw_example.test_sw_repo.services

import org.springframework.stereotype.Service
import sw_example.test_sw_repo.entities.User
import java.util.UUID

@Service
class UserService {

    fun createUser(user : User): User {
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