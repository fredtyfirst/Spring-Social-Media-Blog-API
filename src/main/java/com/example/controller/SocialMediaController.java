package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.repository.AccountRepository;

import java.util.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;
   
    @Autowired
    public SocialMediaController (AccountService accountService){
        this.accountService = accountService;
        
            
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registration(@RequestBody Account account) {
        Account newAccount = accountService.registration(account);
        //return ResponseEntity.status(200).body(newAccount);
         
        if(newAccount!=null){
            return ResponseEntity.status(200).body(newAccount);
        }
        else{
            
            if (accountService.byUsername(account.getUsername())==null){
                return ResponseEntity.status(400).body(null);
                
            }else{
                return ResponseEntity.status(409).body(null);
            }

            
            
                
        }
         
           
    }

}
