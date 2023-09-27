package com.example.loanapplicationbackend.controller;

import com.example.loanapplicationbackend.model.request.LoginReq;
import com.example.loanapplicationbackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/rest/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Authenticates a user with the provided credentials.
     *
     * @param loginReq The request containing the user's login credentials.
     * @return ResponseEntity containing the authentication result.
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginReq loginReq) {
        return authService.login(loginReq);
    }

    /**
     * Registers a new user with the provided credentials.
     *
     * @param loginReq The request containing the user's sign-up credentials.
     * @return ResponseEntity containing the sign-up result.
     */
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody LoginReq loginReq) {
        return authService.signup(loginReq);
    }
}
