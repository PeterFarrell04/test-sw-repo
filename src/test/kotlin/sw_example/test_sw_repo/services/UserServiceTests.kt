package sw_example.test_sw_repo.services

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import sw_example.test_sw_repo.entities.User
import sw_example.test_sw_repo.repositories.UserRepository
import java.util.UUID
import kotlin.test.assertEquals

class UserServiceTests {

    private lateinit var userRepository: UserRepository
    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        userRepository = mock(UserRepository::class.java)
        userService = UserService(userRepository)
    }

    @Test
    fun createUserSavesUser() {
        val user = User(name = "Peter")
        val result = userService.createUser(user)
        assertEquals(user, result)
        verify(userRepository).save(user)
    }

    @Test
    fun getUserReturnsUserWhenFound() {
        val user = User(name = "Peter")
        `when`(userRepository.findAll()).thenReturn(listOf(User(name = "Other"), user))
        val result = userService.getUser(user.id)
        assertEquals(user, result)
    }

    @Test
    fun getUserThrowsNotFoundWhenMissing() {
        `when`(userRepository.findAll()).thenReturn(listOf(User(name = "Other")))
        val exception = assertThrows<ResponseStatusException> {
            userService.getUser(UUID.randomUUID())
        }
        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode)
    }

    @Test
    fun updateUserUpdatesName() {
        val user = User(name = "Peter")
        `when`(userRepository.findAll()).thenReturn(listOf(user))
        val result = userService.updateUser(user.id, User(id = user.id, name = "John"))
        assertEquals(user.id, result.id)
        assertEquals("John", result.name)
        verify(userRepository).save(User(id = user.id, name = "John"))
    }

    @Test
    fun updateUserThrowsBadRequestWhenIdChanged() {
        val user = User(name = "Peter")
        `when`(userRepository.findAll()).thenReturn(listOf(user))
        val exception = assertThrows<ResponseStatusException> {
            userService.updateUser(user.id, User(id = UUID.randomUUID(), name = "John"))
        }
        assertEquals(HttpStatus.BAD_REQUEST, exception.statusCode)
        verify(userRepository, never()).save(user)
    }

    @Test
    fun updateUserThrowsNotFoundWhenMissing() {
        val id = UUID.randomUUID()
        `when`(userRepository.findAll()).thenReturn(listOf())
        val exception = assertThrows<ResponseStatusException> {
            userService.updateUser(id, User(id = id, name = "John"))
        }
        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode)
    }

    @Test
    fun deleteUserDeletesUser() {
        val id = UUID.randomUUID()
        userService.deleteUser(id)
        verify(userRepository).deleteById(id)
    }
}