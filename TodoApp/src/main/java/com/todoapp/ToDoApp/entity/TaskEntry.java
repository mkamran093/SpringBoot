package com.todoapp.ToDoApp.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "task_entries")
@Setter
@Getter
public class TaskEntry {

    @Id
    private ObjectId id;

    private String title;

    private String description;

    private Status status;

    private LocalDateTime date;

}
