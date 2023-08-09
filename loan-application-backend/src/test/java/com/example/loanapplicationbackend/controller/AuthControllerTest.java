package com.example.loanapplicationbackend.controller;

import com.example.loanapplicationbackend.auth.JwtUtil;
import com.example.loanapplicationbackend.model.request.LoginReq;
import com.example.loanapplicationbackend.model.response.ErrorRes;
import com.example.loanapplicationbackend.model.response.LoginRes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        authController = new AuthController(authenticationManager, jwtUtil);
    }

    @Test
    public void testSuccessfulLogin() {
        String email = "test@example.com";
        String password = "password";
        String token = "someToken";

        LoginReq loginReq = new LoginReq(email, password);
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        when(jwtUtil.createToken(any())).thenReturn(token);

        ResponseEntity responseEntity = authController.login(loginReq);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof LoginRes);

        LoginRes loginRes = (LoginRes) responseEntity.getBody();
        assertEquals(email, loginRes.getEmail());
        assertEquals(token, loginRes.getToken());

        verify(authenticationManager).authenticate(any());
        verify(jwtUtil).createToken(any());
    }

    @Test
    public void testInvalidLoginCredentials() {
        String email = "test@example.com";
        String password = "password";

        LoginReq loginReq = new LoginReq(email, password);
        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("Invalid credentials"));

        ResponseEntity responseEntity = authController.login(loginReq);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof ErrorRes);

        ErrorRes errorRes = (ErrorRes) responseEntity.getBody();
        assertEquals("Invalid username or password", errorRes.getMessage());

        verify(authenticationManager).authenticate(any());
    }

    @Test
    public void testExceptionDuringAuthentication() {
        String email = "test@example.com";
        String password = "password";

        LoginReq loginReq = new LoginReq(email, password);
        when(authenticationManager.authenticate(any())).thenThrow(new AuthenticationException("Authentication failed") {
        });

        ResponseEntity responseEntity = authController.login(loginReq);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof ErrorRes);

        ErrorRes errorRes = (ErrorRes) responseEntity.getBody();
        assertEquals("Authentication failed", errorRes.getMessage());

        verify(authenticationManager).authenticate(any());
    }

    @Test
    public void testExceptionDuringJwtTokenCreation() {
        String email = "test@example.com";
        String password = "password";

        LoginReq loginReq = new LoginReq(email, password);
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        when(jwtUtil.createToken(any())).thenThrow(new RuntimeException("Token creation failed"));

        ResponseEntity responseEntity = authController.login(loginReq);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof ErrorRes);

        ErrorRes errorRes = (ErrorRes) responseEntity.getBody();
        assertEquals("Token creation failed", errorRes.getMessage());

        verify(authenticationManager).authenticate(any());
        verify(jwtUtil).createToken(any());
    }

    @Test
    public void testInvalidRequest() {
        LoginReq loginReq = new LoginReq(null, null); // Invalid request with null email and password

        ResponseEntity responseEntity = authController.login(loginReq);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof ErrorRes);

        ErrorRes errorRes = (ErrorRes) responseEntity.getBody();
        assertEquals("Invalid request", errorRes.getMessage());
    }
}

