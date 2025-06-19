package com.documentprocessor.adapter.in;

import com.documentprocessor.adapter.in.dto.CreateUserRequest;
import com.documentprocessor.core.port.in.CreateUserPortIn;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserAdapterInTest {
    @Test
    void shouldCreateUserAndReturnId() {
        var createUserPortIn = mock(CreateUserPortIn.class);
        var request = mock(CreateUserRequest.class);
        var userId = 42L;
        when(createUserPortIn.createUser(request)).thenReturn(userId);
        var controller = new UserAdapterIn(createUserPortIn);
        var result = controller.createUser(request);
        assertThat(result).isEqualTo(userId);
        verify(createUserPortIn).createUser(request);
    }
}
