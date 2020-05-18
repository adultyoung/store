package by.ffefi.store.controller;

import by.ffefi.store.domain.User;
import by.ffefi.store.dto.Role;
import by.ffefi.store.dto.Status;
import by.ffefi.store.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;


    public RegistrationController(UserService userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostMapping
    public User registration(@RequestBody(required = false) User user) {
        if (
                !user.getEmail().isEmpty() &&
                        !user.getUsername().isEmpty() &&
                        !user.getPassword().isEmpty() &&
                        (user.getUsername().length() < 50 &&
                                user.getPassword().length() < 50 &&
                                user.getEmail().length() < 50)
        ) {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setLastVisit(LocalDateTime.now());
            user.setRoles(Collections.singleton(Role.USER));
            user.setCreatedAt(LocalDateTime.now());
            user.setStatus(Collections.singleton(Status.ACTIVE));
            userService.save(user);
            return user;
        }
        return null;
    }
}
