package org.fmartinez.api.note.service.repository;

import org.fmartinez.api.note.service.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepository extends MongoRepository<Note, String> {
}
