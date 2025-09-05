package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CreateJournalRequest;
import com.example.demo.dto.UpdateJournalRequest;
import com.example.demo.entities.Journal;
import com.example.demo.services.JournalService;

@RestController
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping
    public ResponseEntity<List<Journal>> getAllJournals() {
        List<Journal> journals = journalService.getAllJournals();
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Journal> createJournal(@RequestBody CreateJournalRequest req) {
        try {
            Journal journal = journalService.createJournal(req);
            if (journal == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(journal, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Journal> getJournal(@PathVariable("id") String id) {
        Journal journal = journalService.getJournal(id);
        if (journal != null) {
            return new ResponseEntity<>(journal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Journal> updateJournal(@PathVariable("id") String id,
            @RequestBody UpdateJournalRequest journal) {
        try {
            Journal updatedJournal = journalService.updateJournal(id, journal);
            if (updatedJournal == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(updatedJournal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJournal(@PathVariable("id") String id) {
        try {
            journalService.deleteJournal(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
