
package com.example.demo.entities;

import lombok.Data;
import lombok.NonNull;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "journals")
@Data
@NoArgsConstructor
public class Journal {
    @Id
    private ObjectId id;

    @NonNull
    private String title;

    @NonNull
    private String content;

    private Date createdAt;

    private Date updatedAt;

    @Indexed(unique = true)
    @DBRef(db = "users")
    private User user;

    // Expose id as hex string for JSON consumers if needed
    public String getIdString() {
        return id != null ? id.toHexString() : null;
    }
}
