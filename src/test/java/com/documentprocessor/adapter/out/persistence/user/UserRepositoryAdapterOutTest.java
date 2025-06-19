package com.documentprocessor.adapter.out.persistence.user;

import com.documentprocessor.core.domain.User;
import com.documentprocessor.core.mappers.UserMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserRepositoryAdapterOutTest {
    @Test
    void shouldReturnTrueIfUserExistsByEmail() {
        var userMapper = mock(UserMapper.class);
        var userRepository = mock(UserRepository.class);
        var adapter = new UserRepositoryAdapterOut(userMapper, userRepository);
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);
        var result = adapter.existsByEmail("test@example.com");
        assertThat(result).isTrue();
    }

    @Test
    void shouldSaveUser() {
        var userMapper = mock(UserMapper.class);
        var userRepository = mock(UserRepository.class);
        var adapter = new UserRepositoryAdapterOut(userMapper, userRepository);
        var user = new User();
        var entity = mock(UserEntity.class);
        var savedEntity = mock(UserEntity.class);
        var savedUser = new User();
        when(userMapper.toEntity(user)).thenReturn(entity);
        when(userRepository.save(entity)).thenReturn(savedEntity);
        when(userMapper.toDomain(savedEntity)).thenReturn(savedUser);
        var result = adapter.save(user);
        assertThat(result).isEqualTo(savedUser);
    }
}
