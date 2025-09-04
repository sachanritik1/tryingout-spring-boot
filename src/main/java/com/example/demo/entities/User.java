package com.example.demo.entities;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "users")
@Data
public class User {

    @Id
    private ObjectId id;

    @NonNull
    private String name;

    @Indexed(unique = true)
    private String email;

    @NonNull
    private String password;

    @NonNull
    private Date createdAt;

    @NonNull
    private Date updatedAt;
}
