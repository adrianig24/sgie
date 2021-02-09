package es.sgie.back.controller;

import es.sgie.back.controller.error.InfoErrorBody;
import es.sgie.back.service.NoteService;
import es.sgie.back.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.sgie.back.domain.Note;

/**
 * Api for users
 */
@Slf4j
@RestController
@RequestMapping("/api/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/new")
    public ResponseEntity<Object> createDevice(@Valid @RequestBody Note note) {
        try {
            return ResponseEntity.ok(this.noteService.createNote(note));
        } catch (ServiceException e) {
            log.error("Error NoteController.createNote", e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new InfoErrorBody("Error NoteController.createNote", e));
        }
    }

}
