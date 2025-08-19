package com.sparkals.journalApp.controller;

import com.sparkals.journalApp.cache.AppCache;
import com.sparkals.journalApp.entity.User;
import com.sparkals.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
//.requestMatchers("/admin").hasRole("ADMIN") it works bcoz of this in SpringSecurity
    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;

    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userService.getAll();
        if(all!=null&&!all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public void  createUser(@RequestBody User user){
        userService.saveAdmin(user);
    }

    @GetMapping("/clear-app-cache")
    public ResponseEntity<?> clearAppCache(){
        appCache.init();
        return new ResponseEntity<>(appCache, HttpStatus.OK);
    }
}
