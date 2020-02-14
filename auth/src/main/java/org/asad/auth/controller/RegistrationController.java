package org.asad.auth.controller;

import lombok.RequiredArgsConstructor;
import org.asad.auth.model.UserDTO;
import org.asad.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class RegistrationController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.create(userDTO));
    }
}
