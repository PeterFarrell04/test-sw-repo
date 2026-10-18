package sw_example.test_sw_repo.entities

import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "notes")
data class Note(
    @Id val id : UUID,
    val userId : UUID,
    val title : String,
    val body: String
)