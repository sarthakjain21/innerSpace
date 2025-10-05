package com.innerspace.journalApp.controller;

import com.innerspace.journalApp.entity.JournalEntry;
import com.innerspace.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/entries")
    public List<JournalEntry> getAllEntries() {
        return journalEntryService.getAllEntries();
    }

    @PostMapping("/entries")
    public JournalEntry addEntry(@RequestBody JournalEntry entry) {
        entry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(entry);
        return entry;
    }

    @GetMapping("/entries/{id}")
    public JournalEntry getEntryById(@PathVariable ObjectId id) {
        return journalEntryService.getEntryById(id);
    }

    @DeleteMapping("/entries/{id}")
    public void deleteEntry(@PathVariable ObjectId id) {
        journalEntryService.deleteEntry(id);
    }

    @PutMapping("/entries/{id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry updatedEntry) {
        return journalEntryService.updateEntry(id, updatedEntry);
    }
}
