package com.kgodlewski.myapplication.tasks;

import com.kgodlewski.myapplication.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.kgodlewski.myapplication.MyApplicationConstants.messagesTimeout;

@Component
public class ScheduledTasks {

    @Autowired
    MessageRepository messageRepository;

    @Scheduled(fixedRate = 5000)
    public void deleteMessagesFromDBAfter5Minutes() {
        messageRepository.deleteMessageTimeStampBeforeAndById(System.currentTimeMillis()*1000-messagesTimeout);
    }
}