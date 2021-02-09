package es.sgie.back.service.impl;

import es.sgie.back.domain.Note;
import es.sgie.back.exception.ServiceException;
import es.sgie.back.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import es.sgie.back.repository.NoteRepository;
import javax.transaction.Transactional;

@Slf4j
@Service
@Lazy
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepostory;


    @Override
    public Note createNote(Note note) throws ServiceException {
        if (note == null) {
            log.error("Argument 'note' is null in NoteServiceImpl.createNote");
            throw new IllegalArgumentException("Argument 'note' is null in NoteServiceImpl.NoteDevice");
        }

        try {
            return this.noteRepostory.save(note);
        } catch (Exception e) {
            log.error("Error creating Note with data: " + note.toString());
            throw new ServiceException("Error in NoteServiceImpl.createNote: ", e);
        }
    }
    }

