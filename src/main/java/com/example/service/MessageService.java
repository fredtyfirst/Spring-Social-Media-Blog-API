package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.MessageRepository;
import com.example.entity.Message;

import java.util.*;


@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    
    public Message createNewMessage(Message message){
        
        if(!(message.getMessageText().isEmpty()) && message.getMessageText().length()<255){
            return messageRepository.save(message);
        }
        else{
            return null;
        }
        
    }

    public List<Message> getAllMessage(){
        return messageRepository.findAll();
    }

}
