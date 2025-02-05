package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findAllByChatEntityId(Long chatId);
}
