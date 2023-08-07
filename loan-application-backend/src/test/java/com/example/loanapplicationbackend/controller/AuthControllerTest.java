package com.example.loanapplicationbackend.controller;

import com.example.loanapplicationbackend.auth.JwtUtil;
import com.example.loanapplicationbackend.model.User;
import com.example.loanapplicationbackend.model.request.LoginReq;
import com.example.loanapplicationbackend.model.response.ErrorRes;
import com.example.loanapplicationbackend.model.response.LoginRes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginSuccess() {

        Authentication authentication = new UsernamePasswordAuthenticationToken("testuser", "testpassword");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        String token = "test_token";
        when(jwtUtil.createToken(any(User.class))).thenReturn(token);

        LoginReq loginReq = new LoginReq("testuser", "testpassword");
        ResponseEntity response = authController.login(loginReq);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(LoginRes.class, response.getBody().getClass());
        LoginRes loginRes = (LoginRes) response.getBody();
        assertEquals("testuser", loginRes.getUsername());
        assertEquals(token, loginRes.getToken());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtil, times(1)).createToken(any(User.class));
    }

    @Test
    public void testLoginInvalidCredentials() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(BadCredentialsException.class);

        LoginReq loginReq = new LoginReq("invaliduser", "invalidpassword");
        ResponseEntity response = authController.login(loginReq);

        // Assertions
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorRes.class, response.getBody().getClass());
        ErrorRes errorResponse = (ErrorRes) response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getHttpStatus());
        assertEquals("Invalid username or password", errorResponse.getMessage());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    public void testLoginException() {

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new RuntimeException("Some exception"));

        LoginReq loginReq = new LoginReq("testuser", "testpassword");
        ResponseEntity response = authController.login(loginReq);

        // Assertions
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ErrorRes.class, response.getBody().getClass());
        ErrorRes errorResponse = (ErrorRes) response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getHttpStatus());
        assertEquals("Some exception", errorResponse.getMessage());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }



}
