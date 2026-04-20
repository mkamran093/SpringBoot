package net.engineeringdigest.journalapp.controller;

import net.engineeringdigest.journalapp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.HashMap;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("id/{myid}")
    public JournalEntry getJournalEntryById(@PathVariable Long myid) {
        return journalEntries.get(myid);
    }

    @DeleteMapping("id/{myid}")
    public JournalEntry deleteJournalEntry(@PathVariable Long myid) {
        return journalEntries.remove(myid);
    }

    @PutMapping("id/{myid}")
    public JournalEntry updateJournalEntry(@PathVariable Long myid, @RequestBody JournalEntry entry) {
        return journalEntries.put(myid, entry);
    }

}
