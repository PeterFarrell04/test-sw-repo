package sw_example.test_sw_repo.services

import org.springframework.stereotype.Service
import sw_example.test_sw_repo.entities.Note

@Service
class NoteService {

    fun getNotes(): List<Note> {
        return listOf()
    }
}