package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.dto.MessageDto;

import java.util.List;

public interface MessageService {

    List<MessageDto> getAllMessagesFromChat(Long chatId);
}
