package net.engineeringdigest.journalapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")  // to declare that this is a document in mongodb
@Getter
@Setter
public class JournalEntry {

    @Id  // to declare the primary key
    private ObjectId id;

    private String title;

    private String content;

    private LocalDateTime date;

}
