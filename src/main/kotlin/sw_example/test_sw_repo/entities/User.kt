package sw_example.test_sw_repo.entities

import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(
    @Id val id : UUID = UUID.randomUUID(),
    val name : String
)