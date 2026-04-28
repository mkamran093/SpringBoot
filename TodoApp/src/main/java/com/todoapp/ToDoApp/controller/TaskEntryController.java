package com.todoapp.ToDoApp.controller;

import com.todoapp.ToDoApp.entity.TaskEntry;
import com.todoapp.ToDoApp.service.TaskEntryService;
import javafx.concurrent.Task;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskEntryController {

    @Autowired
    private TaskEntryService taskEntryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<TaskEntry> all = taskEntryService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<TaskEntry> postTask(@RequestBody TaskEntry taskEntry) {
        try {
            taskEntry.setDate(LocalDateTime.now());
            taskEntryService.createEntry(taskEntry);
            return new ResponseEntity<>(taskEntry, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<TaskEntry> getTaskById(@PathVariable ObjectId myId) {
        Optional<TaskEntry> entry =  taskEntryService.getById(myId);
        if(entry.isPresent()) {
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{myId}")
    public ResponseEntity<?> updateTask(@PathVariable ObjectId myId, @RequestBody TaskEntry newEntry) {
        TaskEntry old = taskEntryService.getById(myId).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setDescription(newEntry.getDescription() != null && !newEntry.getDescription().isEmpty() ? newEntry.getDescription() : old.getDescription());
            old.setStatus(newEntry.getStatus() != null ? newEntry.getStatus() : old.getStatus());
            taskEntryService.createEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{myId}")
    public ResponseEntity<?> deleteTask(@PathVariable ObjectId myId) {
        Boolean deleted = taskEntryService.delete(myId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
