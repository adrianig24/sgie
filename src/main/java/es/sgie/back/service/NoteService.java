package es.sgie.back.service;

import es.sgie.back.exception.ServiceException;
import es.sgie.back.domain.Note;
import java.util.List;
import java.util.UUID;

/**
 * Service for devices operations
 */
public interface NoteService {

    Note createNote(Note device) throws ServiceException;

}
