package com.sparkals.journalApp.controller;

import com.sparkals.journalApp.entity.User;
import com.sparkals.journalApp.service.UserDetailServiceImpl;
import com.sparkals.journalApp.service.UserService;
import com.sparkals.journalApp.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

@GetMapping("/health-check")
    public String healthCheck(){
    return "Ok";
}
    @PostMapping("/signup")
    public void signup(@RequestBody User user){
     User newUser = new User();
     newUser.setUserName(user.getUserName());
     newUser.setPassword(user.getPassword());
     newUser.setEmail(user.getEmail());
     userService.saveNewUser(newUser);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
    try{
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
       UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
      String jwt = jwtUtil.generateToken(userDetails.getUsername());
       return new ResponseEntity<>(jwt , HttpStatus.OK);
    }catch(Exception e){
      log.error("Exceptioc occured while createAuthenticationToken", e);
      return new ResponseEntity<>("Incorrect username and password", HttpStatus.BAD_REQUEST);
    }
}}
