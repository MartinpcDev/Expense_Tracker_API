package com.martin.api.service;

import com.martin.api.persistence.model.User;

public interface IJwtService {

  String generateToken(User user);

  String extractUsername(String token);

  boolean isTokenValid(String token, User user);
}
