package com.documentprocessor.core.port.in;

import com.documentprocessor.adapter.in.dto.CreateUserRequest;

public interface CreateUserPortIn {

    Long createUser(CreateUserRequest createUserRequest);
}
