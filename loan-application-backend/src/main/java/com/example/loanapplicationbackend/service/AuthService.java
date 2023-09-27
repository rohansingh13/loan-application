package com.example.loanapplicationbackend.service;

import com.example.loanapplicationbackend.auth.JwtUtil;
import com.example.loanapplicationbackend.model.Role;
import com.example.loanapplicationbackend.model.User;
import com.example.loanapplicationbackend.model.request.LoginReq;
import com.example.loanapplicationbackend.model.response.ErrorRes;
import com.example.loanapplicationbackend.model.response.LoginRes;
import com.example.loanapplicationbackend.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;

    public ResponseEntity login(LoginReq loginReq) {
        try {
            String userName = loginReq.getUsername();
            userService.authenticateUser(userName, loginReq.getPassword());

            UserDetails userDetails = userService.loadUserByUsername(userName);
            String token = jwtUtil.createToken(userDetails.getUsername());
            User user = userRepository.findByUsername(userName);
            LoginRes loginRes = new LoginRes(user, token);

            return ResponseEntity.ok(loginRes);

        } catch (UsernameNotFoundException e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "User not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (BadCredentialsException e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Transactional
    public ResponseEntity signup(LoginReq loginReq) {

        String username = loginReq.getUsername();
        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }

        User user = User
                .builder()
                .username(loginReq.getUsername())
                .password(passwordEncoder.encode(loginReq.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        user = userService.save(user);
        String token = jwtUtil.createToken(loginReq.getUsername());
        LoginRes loginRes = new LoginRes(user, token);

        entityManager.flush();
        return ResponseEntity.ok(loginRes);
    }
}
