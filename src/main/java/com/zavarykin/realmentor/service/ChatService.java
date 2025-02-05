package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.dto.ChatDto;

import java.util.List;

public interface ChatService {

    List<ChatDto> getAllChatByUser(String username);

    ChatDto getByOwnerAndMemberOrCreate(String owner, String member);

}
