package net.engineeringdigest.journalapp.service;

import net.engineeringdigest.journalapp.entity.JournalEntry;
import net.engineeringdigest.journalapp.entity.User;
import net.engineeringdigest.journalapp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired   // for dependency injection
    private JournalEntryRepository journalEntryRepository;  // Now you can use it

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        try {
            User user = userService.findByUserName(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);  // we used the built in save method of the Repository
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId myid) {
        return journalEntryRepository.findById(myid);
    }

    public void deleteById(ObjectId myid, String username) {
        User user = userService.findByUserName(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(myid));
        userService.saveUser(user);
        journalEntryRepository.deleteById(myid);
    }
}
