package com.martin.api.controller;

import com.martin.api.service.IAuthService;
import com.martin.api.util.dto.auth.AuthResponse;
import com.martin.api.util.dto.auth.LoginRequest;
import com.martin.api.util.dto.auth.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final IAuthService authService;
  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request) {
    AuthResponse response = authService.register(request);
    logger.info("register: {}", response);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
    AuthResponse response = authService.login(request);
    logger.info("login: {}", response);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }
}
