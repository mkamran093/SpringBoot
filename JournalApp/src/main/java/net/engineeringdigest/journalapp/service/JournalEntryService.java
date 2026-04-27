package net.engineeringdigest.journalapp.service;

import net.engineeringdigest.journalapp.entity.JournalEntry;
import net.engineeringdigest.journalapp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired   // for dependency injection
    private JournalEntryRepository journalEntryRepository;  // Now you can use it

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);  // we used the built in save method of the Repository
    }


    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId myid) {
        return journalEntryRepository.findById(myid);
    }

    public void deleteById(ObjectId myid) {
        journalEntryRepository.deleteById(myid);
    }
}
