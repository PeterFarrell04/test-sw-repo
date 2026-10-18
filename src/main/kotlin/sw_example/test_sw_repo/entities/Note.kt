package sw_example.test_sw_repo.entities

import java.util.UUID

data class Note(
    val id : UUID,
    val userId : UUID,
    val title : String,
    val body: String
)