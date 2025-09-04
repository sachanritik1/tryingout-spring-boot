package com.example.demo.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.example.demo.entities.User;

@Component
public interface UserRepository extends MongoRepository<User, ObjectId> {
}
