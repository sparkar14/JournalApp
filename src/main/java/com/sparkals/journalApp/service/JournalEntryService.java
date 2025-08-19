package com.sparkals.journalApp.service;

import com.sparkals.journalApp.entity.JournalEntry;
import com.sparkals.journalApp.entity.User;
import com.sparkals.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;
    @Transactional //it denotes that if any statement in the methods fail the saved entry in journal_entries will roll back too
    public void  saveEntry(JournalEntry journalEntry , String userName) {
        try{
        User user = userService.findByUsername(userName);
       JournalEntry saved =  journalEntryRepository.save(journalEntry);
       user.getJournalEntries().add(saved);
       userService.saveUser(user);
    }
        catch(Exception e){
        System.out.println(e);
        throw new RuntimeException("An error occur while saving journal entry");
        }
    }

    public void  saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        List<JournalEntry> list = journalEntryRepository.findAll();
        System.out.println("Found " + list.size() + " entries");
        return list;
    }
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    @Transactional
    public boolean deleteById(ObjectId id , String userName) {
        boolean removed = false;
       try{
        User user = userService.findByUsername(userName);
         removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
           if (removed) {
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
        }
        return removed;
    }
       catch(Exception e){
       throw new RuntimeException("An error occur while deleting journal entry",e);
       }
    }

   // public List<JournalEntry> findByUserName(String userName) {}
}
// controller ---> service ---->repository