package controller;


//package com.rbrickks.property.controller;

//import com.rbrickks.property.dto.LoginDTO;
//import com.rbrickks.property.dto.UserDTO;
//import com.rbrickks.property.model.User;
//import com.rbrickks.property.security.JwtUtil;
//import com.rbrickks.property.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import dto.LoginDTO;
import dto.UserDTO;
import model.User;
import security.JwtUtil;
import service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	 @Autowired
	    private UserService userService;

	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtUtil jwtUtil;

	    @PostMapping("/signup")
	    public ResponseEntity<UserDTO> signup(@RequestBody User user) {
	        User savedUser = userService.registerUser(user);
	        UserDTO response = new UserDTO();
	        return ResponseEntity.ok(response);
	    }

	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
	        try {
	            Authentication auth = authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
	            String token = jwtUtil.generateToken(loginDTO.getEmail());
	            return ResponseEntity.ok(token);
	        } catch (AuthenticationException e) {
	            return ResponseEntity.status(401).body("Invalid credentials");
	        }
	    }
}
