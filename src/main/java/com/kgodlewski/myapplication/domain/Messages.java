package com.kgodlewski.myapplication.domain;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.UUID;

@Table
public class Messages {
    @PrimaryKey
    private Integer id;
    private UUID second_id;
    private String title;
    @Email
    private String email;
    private String content;
    private int magic_number;
    private LocalDateTime message_timestamp;


    public Messages() {

    }

    public Messages(Integer id, UUID second_id, String title, String email, String content, int magic_number, LocalDateTime message_timestamp) {
        this.id = id;
        this.second_id = second_id;
        this.title = title;
        this.content = content;
        this.email=email;
        this.magic_number = magic_number;
        this.message_timestamp=message_timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getSecond_id() {
        return second_id;
    }

    public void setSecond_id(UUID second_id) {
        this.second_id = second_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMagic_number() {
        return magic_number;
    }

    public void setMagic_number(int magic_number) {
        this.magic_number = magic_number;
    }

    public LocalDateTime getMessage_timestamp() {
        return message_timestamp;
    }

    public void setMessage_timestamp(LocalDateTime message_timestamp) {
        this.message_timestamp = message_timestamp;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", second_id=" + second_id +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", magic_number=" + magic_number +
                ", message_timestamp=" + message_timestamp +
                '}';
    }
}
