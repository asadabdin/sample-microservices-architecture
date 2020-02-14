package org.asad.customer.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asad.customer.exception.NotFoundException;
import org.asad.customer.model.UserDTO;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserClient {

    private final UserApi userApi;

    public UserDTO getUserById(String id) {
        return ofNullable(id)
                .map(userApi::getById)
                .orElseThrow(() -> new NotFoundException("No such Customer Found"));
    }
}
