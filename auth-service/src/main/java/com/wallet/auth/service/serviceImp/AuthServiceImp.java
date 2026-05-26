package com.wallet.auth.service.serviceImp;

import com.wallet.auth.dto.LoginRequest;
import com.wallet.auth.dto.RegisterRequest;
import com.wallet.auth.entity.User;
import com.wallet.auth.repository.UserRepository;
import com.wallet.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {

    private final UserRepository repository;

    public AuthServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<String> register(RegisterRequest registerRequest) {

        User user = new User();
        try {
            user.setName(registerRequest.getName());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(registerRequest.getPassword());

            repository.save(user);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok("User Registered Successfully");
    }

    @Override
    public ResponseEntity<String> login(LoginRequest request) {

        User user = repository.findByName(request.getName());

        if(user.getPassword().equals(request.getPassword())) {

            return ResponseEntity.ok("Login Success");
        }

        return ResponseEntity.badRequest().body("Invalid Credentials");
    }
}