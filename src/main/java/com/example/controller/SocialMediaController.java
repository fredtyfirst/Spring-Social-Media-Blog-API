package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

import javax.naming.AuthenticationException;

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
    public SocialMediaController (AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registration(@RequestBody Account account) {
        Account newAccount = accountService.registration(account);
         
        if(newAccount!=null){
            return ResponseEntity.status(200).body(newAccount);
        }
        else{
            
            if (accountService.findByUsername(account.getUsername())==null){
                return ResponseEntity.status(400).body(null);
                
            }else{
                return ResponseEntity.status(409).body(null);
            }
 
        }
            
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        Account log = accountService.loginAccount(account.getUsername(), account.getPassword());

        if (log != null) {
            return ResponseEntity.status(200).body(log);
        }

        else{
            return ResponseEntity.status(401).body(null);
        }

    }

    @PostMapping("/messages")
    public ResponseEntity<Message> newMessage(@RequestBody Message message){
        
        if(accountService.findById(message.getPostedBy())!=null){

            Message mess = messageService.createNewMessage(message);
            if(mess!=null){
                return ResponseEntity.status(200).body(mess);
            }
            else{
                return ResponseEntity.status(400).body(null);
            } 
        }
        else{
            return ResponseEntity.status(400).body(null);

        }

    }

    @GetMapping("/messages")
    public List<Message> allMessage(){
        return messageService.getAllMessage();
    }

    @GetMapping("messages/{message_id}")
    public Message messageById(@PathVariable int message_id){
        return messageService.getMessageById(message_id);
    }

    @DeleteMapping("messages/{message_id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable int message_id){
        
        if(messageService.getMessageById(message_id)!=null){
            messageService.deleteMessageById(message_id);
            return ResponseEntity.status(200).body(1);
        }
        else{
            return ResponseEntity.status(200).body(null);
        }
        
    }
    
    @PatchMapping("messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable int message_id, @RequestBody Message message){
        String mess = messageService.updateMessageById(message_id, message);
        if(mess!=null){
            return ResponseEntity.status(200).body(1);
        }
        else{
            return ResponseEntity.status(400).body(null);
        }
        
    }
    
    @GetMapping("/accounts/{account_id}/messages")
    public List<Message> allMessagesById(@PathVariable int account_id){
        List<Message> messageList = messageService.allMessageByUser(account_id);
        return messageList;
        
    }

}
