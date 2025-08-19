package com.sparkals.journalApp.controller;

import com.sparkals.journalApp.entity.JournalEntry;
import com.sparkals.journalApp.entity.User;
import com.sparkals.journalApp.service.JournalEntryService;
import com.sparkals.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class journalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List> getALlJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
       User user =  userService.findByUsername(userName);
        System.out.println("Fetching all journal entries...");
       List<JournalEntry> alt =   user.getJournalEntries();
       if(alt!=null)
         return new ResponseEntity<>(alt,HttpStatus.ACCEPTED);
       else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {//localhost:8080 POST
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntry(myEntry , userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
       User user =  userService.findByUsername(userName);
      List<JournalEntry> collect =  user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());

      if(!collect.isEmpty()) {
          Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
          if (journalEntry.isPresent()) {
              return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
          }
      }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
boolean removed = journalEntryService.deleteById(myId , userName);
if(removed)
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
else
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

 @PutMapping("/id/{myId}")
    public ResponseEntity<?> updateJournalEntryById(
            @PathVariable("myId") ObjectId myId,
            @RequestBody JournalEntry newEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user =  userService.findByUsername(userName);
        List<JournalEntry> collect =  user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());

        if(!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
            if (journalEntry.isPresent()) {
                JournalEntry old = journalEntry.get();
                old.setTitle(newEntry.getTitle()!=null&&!newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
                old.setContent(newEntry.getContent()!=null&&!newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>((old),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
}
