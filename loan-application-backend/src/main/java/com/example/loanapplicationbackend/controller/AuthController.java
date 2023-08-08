package com.example.loanapplicationbackend.controller;

import com.example.loanapplicationbackend.model.request.LoginReq;
import com.example.loanapplicationbackend.model.response.ErrorRes;
import com.example.loanapplicationbackend.model.response.LoginRes;
import com.example.loanapplicationbackend.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/rest/auth")
public class AuthController {

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    private UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private JwtHelper jwtHelper;

    public AuthController(AuthenticationManager authenticationManager, JwtHelper jwtHelper) {
        this.authenticationManager = authenticationManager;
        this.jwtHelper = jwtHelper;
    }

    /*public AuthController(UserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtHelper jwtHelper) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtHelper = jwtHelper;
    }*/

   /* @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginReq loginReq) {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
            String username = authentication.getName();
            logger.info("AuthController : login : username="+ username);
            //User user = new User(username, "");
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String token = jwtHelper.generateToken(userDetails);
            LoginRes loginRes = new LoginRes(username, token);

            return ResponseEntity.ok(loginRes);

        } catch (BadCredentialsException e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Invalid username or password");
            logger.severe("AuthController : login : Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }*/

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@RequestBody LoginReq request) {

        this.doAuthenticate(request.getUsername(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtHelper.generateToken(userDetails);

        LoginRes response = LoginRes.builder()
                .token(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
