package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.entity.VerificationTokenEntity;
import com.zavarykin.realmentor.repository.VerificationTokenRepository;
import com.zavarykin.realmentor.service.VerificationTokenService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

//    private final VerificationTokenRepository verificationTokenRepository;
//
//    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
//        this.verificationTokenRepository = verificationTokenRepository;
//    }
//
//    @Override
//    public VerificationTokenEntity createTokenForUser(UserEntity userEntity) {
//        String token = UUID.randomUUID().toString();
//        VerificationTokenEntity entity = new VerificationTokenEntity();
//        entity.setUserEntity(userEntity);
//        entity.setToken(token);
//        return verificationTokenRepository.save(entity);
//    }
//
//    @Override
//    public VerificationTokenEntity getByUserEntity(UserEntity userEntity) {
//        return verificationTokenRepository.findByUserEntity(userEntity);
//    }
//
//    @Override
//    public VerificationTokenEntity getByToken(String token) {
//        return verificationTokenRepository.findByToken(token);
//    }
}
