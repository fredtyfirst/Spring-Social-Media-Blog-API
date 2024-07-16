package com.example.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Message;


@Repository
public interface MessageRepository extends JpaRepository <Message, Integer>{
    
}



