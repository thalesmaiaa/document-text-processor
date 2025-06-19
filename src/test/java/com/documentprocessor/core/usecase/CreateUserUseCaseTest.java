package com.documentprocessor.core.usecase;

import com.documentprocessor.adapter.in.dto.CreateUserRequest;
import com.documentprocessor.core.domain.User;
import com.documentprocessor.core.exceptions.ConflictedUserException;
import com.documentprocessor.core.mappers.UserMapper;
import com.documentprocessor.core.port.out.UserRepositoryPortOut;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class CreateUserUseCaseTest {
    @Test
    void shouldCreateUserWhenNotExists() {
        var userMapper = mock(UserMapper.class);
        var userRepositoryPortOut = mock(UserRepositoryPortOut.class);
        var useCase = new CreateUserUseCase(userMapper, userRepositoryPortOut);
        var request = mock(CreateUserRequest.class);
        var user = new User();
        user.setId(1L);
        when(userRepositoryPortOut.existsByEmail(any())).thenReturn(false);
        when(userMapper.toDomain(request)).thenReturn(user);
        when(userRepositoryPortOut.save(user)).thenReturn(user);
        var result = useCase.createUser(request);
        assertThat(result).isEqualTo(1L);
    }

    @Test
    void shouldThrowExceptionWhenUserExists() {
        var userMapper = mock(UserMapper.class);
        var userRepositoryPortOut = mock(UserRepositoryPortOut.class);
        var useCase = new CreateUserUseCase(userMapper, userRepositoryPortOut);
        var request = mock(CreateUserRequest.class);
        when(userRepositoryPortOut.existsByEmail(any())).thenReturn(true);
        assertThatThrownBy(() -> useCase.createUser(request))
                .isInstanceOf(ConflictedUserException.class);
    }
}
