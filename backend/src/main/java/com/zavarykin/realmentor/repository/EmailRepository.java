package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<EmailEntity, String> {

    Optional<EmailEntity> findByAddress(String address);
}
