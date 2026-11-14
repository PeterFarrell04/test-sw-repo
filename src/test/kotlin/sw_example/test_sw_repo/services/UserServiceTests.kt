package sw_example.test_sw_repo.services

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.web.server.ResponseStatusException
import sw_example.test_sw_repo.entities.User
import sw_example.test_sw_repo.repositories.UserRepository
import java.util.UUID
import kotlin.test.assertEquals

class UserServiceTests {

    private val userRepository: UserRepository = mock(UserRepository::class.java)
    private val userService = UserService(userRepository)

    @Test
    fun createUserSavesAndReturnsUser() {
        val user = User(name = "Alice")
        val result = userService.createUser(user)
        verify(userRepository).save(user)
        assertEquals(user, result)
    }

    @Test
    fun getUserReturnsUser() {
        val user = User(name = "Alice")
        `when`(userRepository.findAll()).thenReturn(listOf(user))
        val result = userService.getUser(user.id)
        assertEquals(user, result)
    }

    @Test
    fun getUserThrowsWhenNotFound() {
        `when`(userRepository.findAll()).thenReturn(listOf())
        assertThrows<ResponseStatusException> {
            userService.getUser(UUID.randomUUID())
        }
    }

    @Test
    fun updateUserChangesName() {
        val user = User(name = "Alice")
        `when`(userRepository.findAll()).thenReturn(listOf(user))
        val result = userService.updateUser(user.id, User(user.id, "Bob"))
        assertEquals("Bob", result.name)
        assertEquals(user.id, result.id)
    }

    @Test
    fun updateUserThrowsWhenNotFound() {
        `when`(userRepository.findAll()).thenReturn(listOf())
        val id = UUID.randomUUID()
        assertThrows<ResponseStatusException> {
            userService.updateUser(id, User(id, "Bob"))
        }
    }

    @Test
    fun deleteUserDeletesById() {
        val id = UUID.randomUUID()
        userService.deleteUser(id)
        verify(userRepository).deleteById(id)
    }
}