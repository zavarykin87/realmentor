package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.entity.UserTokenEntity;
import com.zavarykin.realmentor.exception.ObjectNotFoundException;
import com.zavarykin.realmentor.exception.TokenExpiredException;
import com.zavarykin.realmentor.repository.UserTokenRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserTokenServiceImpl implements UserTokenService {

    private final UserTokenRepository userTokenRepository;
    private final UserService userService;
    private final long expiry = 24L;

    @Override
    public UserTokenEntity createToken(String username) {
        val user = userService.getByUsername(username);
        val token = UUID.randomUUID().toString();
        return userTokenRepository.save(new UserTokenEntity(token, user, LocalDateTime.now().plusHours(expiry)));
    }

    @Override
    public UserTokenEntity getByToken(String token) {
        val tokenEntity = userTokenRepository.findByToken(token).orElseThrow(() -> new ObjectNotFoundException(token));
        if (tokenEntity.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException();
        } else {
            return tokenEntity;
        }
    }

    @Override
    public void deleteToken(UserTokenEntity entity) {
        userTokenRepository.delete(entity);
    }
}
