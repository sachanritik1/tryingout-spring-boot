package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CreateJournalRequest;
import com.example.demo.dto.UpdateJournalRequest;
import com.example.demo.entities.Journal;
import com.example.demo.entities.User;
import com.example.demo.repositories.JournalRepository;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserService userService;

    public List<Journal> getAllJournals() {
        return journalRepository.findAll();
    }

    @Transactional
    public Journal createJournal(CreateJournalRequest journal) {
        Journal newJournal = new Journal();
        newJournal.setId(ObjectId.get());
        newJournal.setTitle(journal.getTitle());
        newJournal.setContent(journal.getContent());
        Date now = new Date();
        newJournal.setCreatedAt(now);
        newJournal.setUpdatedAt(now);

        Journal savedJournal = journalRepository.save(newJournal);
        userService.addJournalToUser(journal.getAuthorId(), savedJournal);
        return savedJournal;
    }

    public Journal getJournal(String id) {
        return journalRepository.findById(new ObjectId(id)).orElse(null);
    }

    @Transactional
    public void deleteJournal(String id, String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Journal journal = this.getJournal(id);
        if (journal == null) {
            throw new RuntimeException("Journal not found");
        }
        if (!user.getJournals().contains(journal)) {
            throw new RuntimeException("Unauthorized to delete this journal");
        }
        userService.removeJournalFromUser(user.getId().toHexString(), journal);
        journalRepository.deleteById(new ObjectId(id));
    }

    public Journal updateJournal(String id, UpdateJournalRequest journal) {
        Journal existingJournal = this.getJournal(id);
        if (existingJournal == null) {
            throw new RuntimeException("Journal not found");
        }
        if (journal.getTitle() != null) {
            existingJournal.setTitle(journal.getTitle());
        }
        if (journal.getContent() != null) {
            existingJournal.setContent(journal.getContent());
        }

        existingJournal.setUpdatedAt(new Date());
        journalRepository.save(existingJournal);
        return existingJournal;
    }

}
