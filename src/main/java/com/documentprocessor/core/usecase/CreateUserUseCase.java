package com.documentprocessor.core.usecase;

import com.documentprocessor.adapter.in.dto.CreateUserRequest;
import com.documentprocessor.core.exceptions.ConflictedUserException;
import com.documentprocessor.core.mappers.UserMapper;
import com.documentprocessor.core.port.in.CreateUserPortIn;
import com.documentprocessor.core.port.out.UserRepositoryPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserUseCase implements CreateUserPortIn {

    private final UserMapper userMapper;
    private final UserRepositoryPortOut userRepositoryPortOut;

    @Override
    public Long createUser(CreateUserRequest createUserRequest) {
        var userExists = userRepositoryPortOut.existsByEmail(createUserRequest.email());
        if (userExists) {
            throw new ConflictedUserException("email");
        }

        var newUser = userMapper.toDomain(createUserRequest);
        var savedUser = userRepositoryPortOut.save(newUser);
        return savedUser.getId();
    }
}
