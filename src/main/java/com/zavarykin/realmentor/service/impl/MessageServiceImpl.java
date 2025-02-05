package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.dto.MessageDto;
import com.zavarykin.realmentor.entity.MessageEntity;
import com.zavarykin.realmentor.repository.MessageRepository;
import com.zavarykin.realmentor.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageDto> getAllMessagesFromChat(Long chatId) {
        return messageRepository.findAllByChatEntityId(chatId).stream()
                .map(entity -> mapEntityToDto.apply(entity))
                .collect(Collectors.toList());
    }

    private final Function<MessageEntity, MessageDto> mapEntityToDto = entity -> {
        MessageDto dto = new MessageDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setReceiver(entity.getReceiver());
        dto.setSender(entity.getSender());
        dto.setDateTime(entity.getDateTime());
        dto.setChatId(entity.getChatEntity().getId());
        return dto;
    };
}
