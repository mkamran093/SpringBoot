package com.todoapp.ToDoApp.service;

import com.todoapp.ToDoApp.entity.TaskEntry;
import com.todoapp.ToDoApp.repository.TaskEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskEntryService {

    @Autowired
    public TaskEntryRepository taskEntryRepository;

    public TaskEntry createEntry(TaskEntry taskEntry) {
        taskEntryRepository.save(taskEntry);
        return taskEntry;
    }

    public List<TaskEntry> getAll() {
        return taskEntryRepository.findAll();
    }

    public Optional<TaskEntry> getById(ObjectId myId) {
        return taskEntryRepository.findById(myId);
    }

    public Boolean delete(ObjectId myId) {
        try {
            taskEntryRepository.deleteById(myId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public TaskEntry update(TaskEntry newEntry, ObjectId myId) {
        TaskEntry old = taskEntryRepository.findById(myId).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setDescription(newEntry.getDescription() != null && !newEntry.getDescription().isEmpty() ? newEntry.getDescription() : old.getDescription());
            old.setStatus(newEntry.getStatus() != null ? newEntry.getStatus() : old.getStatus());
            taskEntryRepository.save(old);
            return old;
        }
        return newEntry;
    }

}
