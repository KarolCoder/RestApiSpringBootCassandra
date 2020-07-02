package com.kgodlewski.myapplication.rest;

import com.kgodlewski.myapplication.service.MailService;
import com.kgodlewski.myapplication.domain.MagicNumberBody;
import com.kgodlewski.myapplication.domain.Messages;
import com.kgodlewski.myapplication.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.kgodlewski.myapplication.validation.MyApplicationValidation.isValidEmailAddress;

@CrossOrigin(origins = "http://localhost:8082/")
@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MailService mailService;

    @GetMapping("/messages/{emailValue}")
    public ResponseEntity<List<Messages>> getMessageByEmail(@PathVariable("emailValue") String emailValue, @RequestParam(required = false) Integer limit) {
         try {
            List<Messages> messages = new ArrayList<>();
            if (emailValue != null) {
                if (limit!=null && limit> 0) {
                    messages.addAll(messageRepository.findByEmailWithLimit(emailValue,limit));
                }else{
                    messages.addAll(messageRepository.findByEmail(emailValue));
                }
            }
            if (messages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/message")
    public ResponseEntity<Messages> createMessage(@RequestBody Messages message) {
        try {
            if(!isValidEmailAddress(message.getEmail())) {
                return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
            }
            Messages _message = messageRepository.save(new Messages(1,UUID.randomUUID(), message.getTitle(), message.getEmail(), message.getContent(),message.getMagic_number(),LocalDateTime.now()));
            return new ResponseEntity<>(_message, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/send")
    public ResponseEntity<List<Messages>> sendMessages(@RequestBody MagicNumberBody magic_number) throws MessagingException {
        Integer magicNumber = magic_number.getMagic_number();
        try {
                List<Messages> messages = new ArrayList<>();
                messages.addAll(messageRepository.findByMagicNumber(magicNumber));
                if (messages.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                for (Messages message : messages) {
                    mailService.sendMail(message.getEmail(),message.getTitle(),message.getContent(),false);
                    messageRepository.deleteMessageByMagicNumber( message.getSecond_id(),magicNumber);
                }

            return new ResponseEntity<>(messages, HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
