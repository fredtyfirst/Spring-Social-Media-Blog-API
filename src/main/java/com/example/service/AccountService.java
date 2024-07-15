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

    public List<Account> getAccountList(){
        return accountRepository.findAll();
    }

    public Account byUsername(String username){
        return accountRepository.findByUsername(username).orElse(null);
    }


    public Account registration(Account account){
        //return accountRepository.save(account);
        
        if(byUsername(account.getUsername())==null && !(account.getUsername().isEmpty()) && (account.getPassword()).length()>=4){
            
            return accountRepository.save(account);
        }
       
        else{
            return null;
        }
        
        
    }

}
