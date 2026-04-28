package net.engineeringdigest.journalapp.service;

import net.engineeringdigest.journalapp.entity.JournalEntry;
import net.engineeringdigest.journalapp.entity.User;
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

    @Autowired
    private UserService userService;

    public JournalEntry saveEntry(JournalEntry journalEntry) {
        return journalEntryRepository.save(journalEntry);  // we used the built in save method of the Repository
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
