package com.martin.api.service.impl;

import com.martin.api.exception.InvalidAuthException;
import com.martin.api.persistence.model.User;
import com.martin.api.persistence.repository.UserRepository;
import com.martin.api.service.IAuthService;
import com.martin.api.service.IJwtService;
import com.martin.api.util.dto.AuthResponse;
import com.martin.api.util.dto.LoginRequest;
import com.martin.api.util.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final IJwtService jwtService;

  @Override
  public AuthResponse register(RegisterRequest request) {
    if (userRepository.existsByEmailIgnoreCase(request.email())
        || userRepository.existsByUsernameIgnoreCase(request.username())) {
      throw new InvalidAuthException("Email o Username ya estan en uso");
    }
    User user = new User();
    user.setUsername(request.username());
    user.setEmail(request.email());
    user.setPassword(passwordEncoder.encode(request.password()));
    User createdUser = userRepository.save(user);
    String token = jwtService.generateToken(createdUser);
    return new AuthResponse(token);
  }

  @Override
  public AuthResponse login(LoginRequest request) {
    return null;
  }
}
