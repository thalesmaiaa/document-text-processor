package com.documentprocessor.core.mappers;

import com.documentprocessor.adapter.in.dto.CreateUserRequest;
import com.documentprocessor.adapter.out.persistence.user.UserEntity;
import com.documentprocessor.core.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserEntity userEntity);

    User toDomain(CreateUserRequest request);

    UserEntity toEntity(User user);
}
