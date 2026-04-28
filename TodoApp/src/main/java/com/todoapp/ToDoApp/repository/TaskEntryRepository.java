package com.todoapp.ToDoApp.repository;

import com.todoapp.ToDoApp.entity.TaskEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskEntryRepository extends MongoRepository<TaskEntry, ObjectId> {
}
