package com.sparkals.journalApp.cache;

import com.mongodb.client.FindIterable;
import com.sparkals.journalApp.entity.ConfigJournalAppEntity;
import com.sparkals.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        weather_api;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String , String> APP_CACHE;

    @PostConstruct
    public void init(){
        APP_CACHE = new HashMap<>();
       List<ConfigJournalAppEntity> all =  configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity app : all) {
            APP_CACHE.put(app.getKey(),app.getValue());
        }
    }

}
