package sw_example.test_sw_repo.services

import org.springframework.stereotype.Service
import sw_example.test_sw_repo.entities.Note
import sw_example.test_sw_repo.repositories.NoteRepository

@Service
class NoteService(
    val noteRepository: NoteRepository
) {

    fun saveNote(note: Note) : Note {
        noteRepository.save(note)
        return note
    }

    fun getNotes() {
        //TODO: DEV-XX
    }
}