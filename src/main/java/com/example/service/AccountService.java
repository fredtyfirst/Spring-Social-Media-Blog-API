package com.example.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    private MessageService messageService;

   
    public Account findByUsername(String username){
        return accountRepository.findByUsername(username).orElse(null);
    }


    public Account registration(Account account){
        
        if(findByUsername(account.getUsername())==null && !(account.getUsername().isEmpty()) && (account.getPassword()).length()>=4){
            
            return accountRepository.save(account);
        }
       
        else{
            return null;
        }
 
    }

    public Account loginAccount(String username, String password) throws ArithmeticException{
        return accountRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

    public Account findById(int id){
        return accountRepository.findById(id).orElse(null);
    }


}
