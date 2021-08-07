package com.traders.guild.restcontrollers;

import com.traders.guild.domain.Note;
import com.traders.guild.services.NoteService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteRestController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/")
    public Note createDefaultNote(){
        return new Note("default note is created");
    }

    @GetMapping("/list")
    public ResponseEntity<List<Note>> getAllNotes(){
        return  new ResponseEntity<List<Note>>(noteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id){
        return new ResponseEntity<Note>(noteService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Note> createNewNote(@RequestBody Note note){
        return new ResponseEntity<Note>(noteService.saveNewNote(note), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") Long id, @RequestBody Note note){
       return new ResponseEntity<Note>(noteService.updateNote(note, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable("id") Long id){
        noteService.deleteNote(id);
        return new ResponseEntity<String>("Note deleted successfully!", HttpStatus.OK);
    }
}
