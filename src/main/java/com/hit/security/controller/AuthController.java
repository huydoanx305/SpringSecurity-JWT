package com.hit.security.controller;

import com.hit.security.Utils.JwtTokenUtil;
import com.hit.security.dto.AccountDTO;
import com.hit.security.dto.response.AuthenticationResponse;
import com.hit.security.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDTO accountDTO) throws Exception {
        try {
            authenticationManager.authenticate (
                    new UsernamePasswordAuthenticationToken(accountDTO.getUsername(), accountDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(accountDTO.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok().body(new AuthenticationResponse(jwt));
    }
}

