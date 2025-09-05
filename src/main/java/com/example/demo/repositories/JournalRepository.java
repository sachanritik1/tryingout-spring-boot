package com.example.demo.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entities.Journal;

public interface JournalRepository extends MongoRepository<Journal, ObjectId> {

}