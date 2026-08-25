package com.wallet.auth.service.serviceImp;

import com.wallet.auth.client.WalletClient;
import com.wallet.auth.dto.LoginRequest;
import com.wallet.auth.dto.RegisterRequest;
import com.wallet.auth.dto.requestDto.WalletCreateRequestDto;
import com.wallet.auth.entity.User;
import com.wallet.auth.repository.UserRepository;
import com.wallet.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {

    private final UserRepository repository;
    private final WalletClient walletClient;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImp(UserRepository repository, WalletClient walletClient, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.walletClient = walletClient;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<String> register(RegisterRequest registerRequest) {

        User user = new User();
        try {

            String encoderPassword = passwordEncoder.encode(registerRequest.getPassword());

            user.setName(registerRequest.getName());
            user.setEmail(registerRequest.getEmail());
            user.setNormalPassword(registerRequest.getPassword());
            user.setPassword(encoderPassword);

            user = repository.save(user);

            WalletCreateRequestDto requestDto = new WalletCreateRequestDto();
            requestDto.setUserId(user.getId());

//            walletClient.createWallet(requestDto);

            return ResponseEntity.ok("User Registered Successfully");


        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("User Register Failed");
        }
    }

    @Override
    public ResponseEntity<String> login(LoginRequest request) {

        System.out.println("request = " + request);
        User user = repository.findByName(request.getName());

        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid Credentials");
        }

        boolean result = passwordEncoder.matches(request.getPassword(), user.getPassword());

        System.out.println("result = " + result);

        if (result) {
            return ResponseEntity.ok("Login Success");
        }

        return ResponseEntity.badRequest().body("Invalid Credentials");
    }



}