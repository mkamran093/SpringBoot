package com.todoapp.ToDoApp.controller;

import com.todoapp.ToDoApp.entity.TaskEntry;
import com.todoapp.ToDoApp.service.TaskEntryService;
import javafx.concurrent.Task;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<TaskEntry> getAll() {
        return taskEntryService.getAll();
    }

    @PostMapping
    public TaskEntry postTask(@RequestBody TaskEntry taskEntry) {
        taskEntry.setDate(LocalDateTime.now());
        return taskEntryService.createEntry(taskEntry);
    }

    @GetMapping("id/{myId}")
    public Optional<TaskEntry> getTaskById(@PathVariable ObjectId myId) {
        return taskEntryService.getById(myId);
    }

    @PutMapping("id/{myId}")
    public TaskEntry updateTask(@PathVariable ObjectId myId, @RequestBody TaskEntry taskEntry) {
        return taskEntryService.update(taskEntry, myId);
    }

    @DeleteMapping("id/{myId}")
    public Boolean deleteTask(@PathVariable ObjectId myId) {
        return taskEntryService.delete(myId);
    }

}
