package org.asad.auth.controller;

import lombok.RequiredArgsConstructor;
import org.asad.auth.model.UserDTO;
import org.asad.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> read(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(userService.get(id));
    }
}
