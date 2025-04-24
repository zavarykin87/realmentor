package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "chats")
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner")
    private String owner;

    @Column(name = "member")
    private String member;

    @OneToMany(mappedBy = "chatEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MessageEntity> messages;

    public ChatEntity(String owner, String member) {
        this.owner = owner;
        this.member = member;
    }

    public ChatEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

    public void addMessage(MessageEntity message) {
        this.messages.add(message);
        message.setChatEntity(this);
    }

    public void removeMessage(MessageEntity message) {
        this.messages.remove(message);
        message.setChatEntity(null);
    }
}
