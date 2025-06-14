package com.documentprocessor.adapter.out.persistence.user;

import com.documentprocessor.core.domain.User;
import com.documentprocessor.core.mappers.UserMapper;
import com.documentprocessor.core.port.out.UserRepositoryPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapterOut implements UserRepositoryPortOut {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User save(User user) {
        var savedUser = userRepository.save(userMapper.toEntity(user));
        return userMapper.toDomain(savedUser);
    }
}