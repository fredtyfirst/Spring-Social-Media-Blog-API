package com.example.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Message;
import java.util.*;


@Repository
public interface MessageRepository extends JpaRepository <Message, Integer>{


    List<Message> findByPostedBy (int accountId);

}



