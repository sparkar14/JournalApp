package com.sparkals.journalApp.repository;

import com.sparkals.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//inside MongoRepository many methods are defined already thats why we extend
@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry , ObjectId> {


}
