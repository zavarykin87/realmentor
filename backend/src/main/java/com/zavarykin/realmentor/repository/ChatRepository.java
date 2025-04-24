package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

    List<ChatEntity> findAllByOwnerOrMember(String owner, String member);

    Optional<ChatEntity> findByOwnerAndMember(String owner, String member);
}
