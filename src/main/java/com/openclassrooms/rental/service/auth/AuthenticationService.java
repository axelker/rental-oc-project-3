package com.openclassrooms.rental.service.auth;

import java.util.NoSuchElementException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.rental.dto.request.AuthLoginRequest;
import com.openclassrooms.rental.dto.request.AuthRegisterRequest;
import com.openclassrooms.rental.dto.response.AuthResponse;
import com.openclassrooms.rental.exception.user.UserAlreadyExistsException;
import com.openclassrooms.rental.model.UserEntity;
import com.openclassrooms.rental.repository.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JWTService jwtService,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(AuthRegisterRequest request) throws Exception {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exist.");
        }

        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user);

        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse authenticate(AuthLoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));

            UserEntity user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new NoSuchElementException("User not found after authentication"));

            String token = jwtService.generateToken(user);
            return AuthResponse.builder().token(token).build();

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password.");
        }
    }

}