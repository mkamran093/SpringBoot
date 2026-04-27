package com.todoapp.ToDoApp.controller;

import com.todoapp.ToDoApp.entity.TaskEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskEntryController {

    private Map<Long, TaskEntry> taskEntries = new HashMap<>();

    @GetMapping
    public List<TaskEntry> getAll() {
        return new ArrayList<>(taskEntries.values());
    }

    @PostMapping
    public boolean postTask(@RequestBody TaskEntry task) {
        taskEntries.put(task.getId(), task);
        return true;
    }

    @GetMapping("id/{myid}")
    public TaskEntry getTaskById(@PathVariable Long myid) {
        return taskEntries.get(myid);
    }

    @PutMapping("id/{myid}")
    public TaskEntry updateTask(@PathVariable Long myid, @RequestBody TaskEntry entry) {
        return taskEntries.put(myid, entry);
    }

    @DeleteMapping("id/{myid}")
    public TaskEntry deleteTask(@PathVariable Long myid) {
        return taskEntries.remove(myid);
    }




}
