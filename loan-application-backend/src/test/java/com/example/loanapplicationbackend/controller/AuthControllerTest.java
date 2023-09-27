package com.example.loanapplicationbackend.controller;

import com.example.loanapplicationbackend.model.User;
import com.example.loanapplicationbackend.model.request.LoginReq;
import com.example.loanapplicationbackend.model.response.ErrorRes;
import com.example.loanapplicationbackend.model.response.LoginRes;
import com.example.loanapplicationbackend.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setup() {
        authController = new AuthController(authService);
    }

    @Test
    public void testLogin_Success() {
        LoginReq loginReq = new LoginReq("user1", "user123");

        User user = new User();
        user.setUsername("user1");

        LoginRes loginRes = new LoginRes(user, "token");

        when(authService.login(any(LoginReq.class))).thenReturn(ResponseEntity.ok(loginRes));

        ResponseEntity response = authController.login(loginReq);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof LoginRes);

        LoginRes actualLoginRes = (LoginRes) response.getBody();
        assertNotNull(actualLoginRes);
        assertEquals("user1", actualLoginRes);

        verify(authService, times(1)).login(loginReq);
    }

    @Test
    public void testSignup_Success() {
        LoginReq signupReq = new LoginReq("username", "password");

        User user = new User();
        user.setUsername("username");

        LoginRes loginRes = new LoginRes(user, "token");

        when(authService.signup(any(LoginReq.class))).thenReturn(ResponseEntity.ok(loginRes));

        ResponseEntity response = authController.signup(signupReq);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof LoginRes);

        LoginRes actualLoginRes = (LoginRes) response.getBody();
        assertNotNull(actualLoginRes);
        assertEquals("username", actualLoginRes);

        verify(authService, times(1)).signup(signupReq);
    }


    @Test
    public void testLogin_Failure_BadCredentials() {
        LoginReq loginReq = new LoginReq("username", "wrongpassword");

        when(authService.login(any(LoginReq.class))).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorRes(HttpStatus.BAD_REQUEST, "Invalid username or password")));

        ResponseEntity response = authController.login(loginReq);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorRes);

        ErrorRes errorResponse = (ErrorRes) response.getBody();
        assertNotNull(errorResponse);
        assertEquals("Invalid username or password", errorResponse.getMessage());

        verify(authService, times(1)).login(loginReq);
    }
}

