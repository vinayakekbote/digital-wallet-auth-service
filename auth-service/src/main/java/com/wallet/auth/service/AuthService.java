package com.wallet.auth.service;

import com.wallet.auth.dto.LoginRequest;
import com.wallet.auth.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    public ResponseEntity<String> register(RegisterRequest registerRequest);

    public ResponseEntity<String> login(LoginRequest loginReqeuest);

}
