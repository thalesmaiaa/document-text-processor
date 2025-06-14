package com.documentprocessor.adapter.in;

import com.documentprocessor.adapter.in.dto.CreateUserRequest;
import com.documentprocessor.core.port.in.CreateUserPortIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserAdapterPortIn {

    private final CreateUserPortIn createUserPortIn;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody @Valid CreateUserRequest request) {
        return createUserPortIn.createUser(request);
    }
}
