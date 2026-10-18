package sw_example.test_sw_repo.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sw_example.test_sw_repo.services.NoteService

@RestController
@RequestMapping("/api/notes")
class NoteController(
    private val noteService: NoteService
) {

}