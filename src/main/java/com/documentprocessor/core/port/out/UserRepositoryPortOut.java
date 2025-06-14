package com.documentprocessor.core.port.out;

import com.documentprocessor.core.domain.User;

public interface UserRepositoryPortOut {

    Boolean existsByEmail(String email);

    User save(User user);
}
