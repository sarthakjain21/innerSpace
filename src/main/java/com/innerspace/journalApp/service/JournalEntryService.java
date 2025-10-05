package com.innerspace.journalApp.service;

import com.innerspace.journalApp.entity.JournalEntry;
import com.innerspace.journalApp.repository.journalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalEntryService {

    @Autowired
    private journalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry entry) {
        journalEntryRepository.save(entry);
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry getEntryById(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    public void deleteEntry(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }

    public JournalEntry updateEntry(ObjectId id, JournalEntry updatedEntry) {
        return journalEntryRepository.findById(id).map(entry -> {
            entry.setTitle(updatedEntry.getTitle());
            entry.setContent(updatedEntry.getContent());
            return journalEntryRepository.save(entry);
        }).orElse(null);
    }

}
