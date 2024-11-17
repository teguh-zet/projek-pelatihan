package com.perpustakaan.susiharyati.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perpustakaan.susiharyati.models.Message;
import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Integer>{
    List<Message> findByStatusMessage(String statusMessage);
}
