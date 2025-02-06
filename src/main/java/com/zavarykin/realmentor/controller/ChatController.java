package com.zavarykin.realmentor.controller;

import com.zavarykin.realmentor.dto.ChatDto;
import com.zavarykin.realmentor.dto.MessageDto;
import com.zavarykin.realmentor.entity.ChatEntity;
import com.zavarykin.realmentor.entity.MessageEntity;
import com.zavarykin.realmentor.repository.ChatRepository;
import com.zavarykin.realmentor.service.ChatService;
import com.zavarykin.realmentor.service.MessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Controller
public class ChatController {

    private final ChatService chatService;
    private final ChatRepository chatRepository;
    private final MessageService messageService;

    public ChatController(ChatService chatService,
                          ChatRepository chatRepository,
                          MessageService messageService) {
        this.chatService = chatService;
        this.chatRepository = chatRepository;
        this.messageService = messageService;
    }

    @MessageMapping("/chat.sendMessage/{receiver}")
    @SendTo("/topic/{receiver}")
    public MessageDto getMessage(@DestinationVariable String receiver, MessageDto chatMessage) {
        // получить сообщение, добавить в чат и сохранить в базу
        ChatEntity chatEntity = chatRepository.findById(chatMessage.getChatId()).get();
        MessageEntity message = new MessageEntity();
        message.setSender(chatMessage.getSender());
        message.setReceiver(chatMessage.getReceiver());
        message.setDateTime(LocalDateTime.now());
        message.setContent(chatMessage.getContent());
        message.setChatEntity(chatEntity);
        chatEntity.addMessage(message);
        chatRepository.save(chatEntity);
        return chatMessage;
    }

    // страница со списком чатов пользователя
    @GetMapping("/messages")
    public String messagesPage(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        List<ChatDto> chats = chatService.getAllChatByUser(username);
        model.addAttribute("chats", chats);
        return "messages";
    }

    // страница с чатом
    @GetMapping("chat/{receiver}")
    public String chatPage(@PathVariable(name = "receiver") String receiver, Model model) {
        if (receiver.equals("info")) { // info - служебный запрос
            return "chat";
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String sender = userDetails.getUsername();

        // получить чат или создать
        Long chatId = chatService.getByOwnerAndMemberOrCreate(sender, receiver).getId();
        // получить все сообщения из чата
        List<MessageDto> messages = messageService.getAllMessagesFromChat(chatId);

        model.addAttribute("receiver", receiver);
        model.addAttribute("chatId", chatId);
        model.addAttribute("messages", messages);
        return "chat";
    }

}
