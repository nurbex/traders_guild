package com.traders.guild.services;

import com.traders.guild.domain.Note;
import com.traders.guild.exceptions.ResourceNotFoundException;
import com.traders.guild.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    public List<Note> findAll(){
        return noteRepository.findAll();
    }

    public Note saveNewNote(Note note) {
        return noteRepository.save(note);
    }

    public Note findById(Long id) {
/*        Optional<Note> note=noteRepository.findById(id);
        if(note.isPresent()){
            return note.get();
        }else{
            throw new ResourceNotFoundException("Note","Id", id);
        }*/
        return noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "Id", id));
    }

    public Note updateNote(Note note, Long id){
        Note existingNote= noteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Note","Id", id));
        existingNote.setNote(note.getNote());
        existingNote.setId(note.getId());
        noteRepository.save(existingNote);
        return existingNote;
    }

    public void deleteNote(Long id){
        noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        noteRepository.deleteById(id);
    }
}
