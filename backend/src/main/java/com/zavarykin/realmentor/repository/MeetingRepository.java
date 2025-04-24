package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<MeetingEntity, Long> {
}
