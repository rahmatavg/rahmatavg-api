package com.rahmatavg.api.login.controllers;

import com.rahmatavg.api.login.model.*;
import com.rahmatavg.api.login.repository.GenerateTokenRepository;
import com.rahmatavg.api.login.repository.GenerateTokenRepositoryImp;
import com.rahmatavg.api.login.security.services.MyUserDetailsService;
import com.rahmatavg.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController()
public class LoginControllers {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private GenerateTokenRepository generateTokenRepository;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @RequestMapping(value = "/authenticate", method =  RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrenct username or password");
        }

        final Login userDetails = (Login) myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(userDetails.getId());
        loginResponse.setEmail(userDetails.getEmail());
        loginResponse.setName(userDetails.getName());
        loginResponse.setToken(jwt);
        loginResponse.setTokenType("Bearer");

        GenerateToken token = new GenerateToken();
        token.setUserId(loginResponse.getId());
        token.setToken(loginResponse.getTokenType()+" "+loginResponse.getToken());
        generateTokenRepository.saveToken(token);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/token", method =  RequestMethod.GET)
    public String getToken(@RequestParam("userId") Long userId) {
        String token = generateTokenRepository.getToken(userId);
        if (token == null) {
            return "not-found";
        }
        return token;
    }

    @RequestMapping(value = "/logout-api", method =  RequestMethod.POST)
    public String logoutApi(@RequestParam("userId") Long userId) {
        generateTokenRepository.deleteToken(userId);
        return "berhasil";
    }

}
