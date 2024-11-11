package com.martin.api.service;

import com.martin.api.util.dto.auth.AuthResponse;
import com.martin.api.util.dto.auth.LoginRequest;
import com.martin.api.util.dto.auth.RegisterRequest;

public interface IAuthService {

  AuthResponse register(RegisterRequest request);

  AuthResponse login(LoginRequest request);
}
