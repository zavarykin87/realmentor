package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.dto.RequestToMentoringDto;

import java.util.List;

public interface RequestToMentoringService {

    void save(RequestToMentoringDto dto);

    List<RequestToMentoringDto> getAllByMentor(String mentorname);

    void deleteById(Long id);

    void approve(Long id) throws Exception;
}
