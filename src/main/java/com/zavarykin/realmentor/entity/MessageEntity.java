package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender", nullable = false)
    private String sender;

    @Column(name = "receiver", nullable = false)
    private String receiver;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "date_time", nullable = false)
    private OffsetDateTime dateTime;

    @ManyToOne()
    @JoinColumn(name = "chat_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_message_to_chat"))
    private ChatEntity chatEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ChatEntity getChatEntity() {
        return chatEntity;
    }

    public void setChatEntity(ChatEntity chatEntity) {
        this.chatEntity = chatEntity;
    }
}
