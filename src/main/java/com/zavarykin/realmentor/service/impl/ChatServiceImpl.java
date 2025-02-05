package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.dto.ChatDto;
import com.zavarykin.realmentor.entity.ChatEntity;
import com.zavarykin.realmentor.repository.ChatRepository;
import com.zavarykin.realmentor.service.ChatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public List<ChatDto> getAllChatByUser(String username) {
        return chatRepository.findAllByOwnerOrMember(username, username).stream()
                .map(chat -> mapEntityToDto.apply(chat))
                .collect(Collectors.toList());
    }

    @Override
    public ChatDto getByOwnerAndMemberOrCreate(String owner, String member) {
        Optional<ChatEntity> optionalChat = chatRepository.findByOwnerAndMember(owner, member);
        if (optionalChat.isEmpty()) {
            optionalChat = chatRepository.findByOwnerAndMember(member, owner);
        } if (optionalChat.isEmpty()) {
            ChatEntity chatEntity = new ChatEntity(owner, member);
            chatEntity = chatRepository.save(chatEntity);
            return mapEntityToDto.apply(chatEntity);
        }
        return mapEntityToDto.apply(optionalChat.get());
    }



    private final Function<ChatEntity, ChatDto> mapEntityToDto = entity -> {
        ChatDto dto = new ChatDto();
        dto.setId(entity.getId());
        dto.setOwner(entity.getOwner());
        dto.setMember(entity.getMember());
        return dto;
    };
}
