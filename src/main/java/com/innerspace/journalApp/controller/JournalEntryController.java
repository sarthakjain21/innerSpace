package com.innerspace.journalApp.controller;

import com.innerspace.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping("/entries")
    public List<JournalEntry> getAllEntries() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping("/entries")
    public void addEntry(@RequestBody JournalEntry entry) {
        journalEntries.put(entry.getId(), entry);
    }

    @GetMapping("/entries/{id}")
    public JournalEntry getEntryById(@PathVariable Long id) {
        return journalEntries.get(id);
    }

    @DeleteMapping("/entries/{id}")
    public void deleteEntry(@PathVariable Long id) {
        journalEntries.remove(id);
    }

    @PutMapping("/entries/{id}")
    public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry updatedEntry) {
        if (journalEntries.containsKey(id)) {
            journalEntries.put(id, updatedEntry);
            return updatedEntry;
        }
        return null;
    }
}
