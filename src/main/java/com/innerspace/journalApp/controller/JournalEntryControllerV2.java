package com.innerspace.journalApp.controller;

import com.innerspace.journalApp.entity.JournalEntry;
import com.innerspace.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/entries")
    public ResponseEntity<List<JournalEntry>> getAllEntries() {
        try {
            List<JournalEntry> entries = journalEntryService.getAllEntries();
            return new ResponseEntity<>(entries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/entries")
    public ResponseEntity<JournalEntry> addEntry(@RequestBody JournalEntry entry) {
        try {
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(entry);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/entries/{id}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId id) {
        try {
            Optional<JournalEntry> journalEntry = Optional.ofNullable(journalEntryService.getEntryById(id));
            if(journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/entries/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable ObjectId id) {
        try {
            journalEntryService.deleteEntry(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/entries/{id}")
    public ResponseEntity<JournalEntry> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry updatedEntry) {
        try {
            JournalEntry updated = journalEntryService.updateEntry(id, updatedEntry);
            if (updated != null) {
                return new ResponseEntity<>(updated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
