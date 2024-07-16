package com.example.repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;
import com.example.entity.Message;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    Optional <Account> findByUsername(String username);

    Optional <Account> findByUsernameAndPassword(String username, String password);

}
