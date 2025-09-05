package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import lombok.NonNull;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    private ObjectId id;

    @NonNull
    private String name;

    @Indexed(unique = true)
    private String email;

    @NonNull
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    private List<String> roles;

    private Date createdAt;

    private Date updatedAt;

    @DBRef
    private ArrayList<Journal> journals = new ArrayList<>();

    // Expose id as hex string for JSON consumers if needed
    public String getIdString() {
        return id != null ? id.toHexString() : null;
    }
}
