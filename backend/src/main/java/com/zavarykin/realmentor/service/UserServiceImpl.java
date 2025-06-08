package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.entity.RoleEntity;
import com.zavarykin.realmentor.entity.RoleName;
import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //TODO удалить в будущем
    @PostConstruct
    private void createAdmin() {
        UserEntity user = new UserEntity();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("admin@mail.ru");
        user.addRole(new RoleEntity(RoleName.ADMIN));
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(); //TODO throw exception
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }
}
