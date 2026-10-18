package sw_example.test_sw_repo.repositories

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import sw_example.test_sw_repo.entities.User
import java.util.UUID

@Repository
interface UserRepository : MongoRepository<User, UUID>