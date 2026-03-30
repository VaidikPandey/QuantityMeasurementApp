package com.QuantityMeasurementApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.QuantityMeasurementApp.model.AuthResponse;
import com.QuantityMeasurementApp.model.LoginRequest;
import com.QuantityMeasurementApp.model.RegisterRequest;
import com.QuantityMeasurementApp.model.User;
import com.QuantityMeasurementApp.repository.UserRepository;
import com.QuantityMeasurementApp.security.JwtService;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public AuthResponse register(RegisterRequest request) {

		// Check if user already exists
		if (userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("User already exists with email: " + request.getEmail());
		}

		// Hash the password before saving
		String hashedPassword = passwordEncoder.encode(request.getPassword());

		// Create and save new user
		User user = new User(request.getEmail(), request.getName(), hashedPassword);
		userRepository.save(user);

		// Generate JWT token
		String token = jwtService.generateToken(request.getEmail());

		System.out.println("New user registered: " + request.getEmail());

		return new AuthResponse(token, request.getEmail(), request.getName(), "Registration successful");
	}

	public AuthResponse login(LoginRequest request) {

		// Find user by email
		Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

		if (existingUser.isEmpty()) {
			throw new RuntimeException("User not found with email: " + request.getEmail());
		}

		User user = existingUser.get();

		// Check if user registered with Google (no password)
		if (user.getProvider().equals("google")) {
			throw new RuntimeException("Please login with Google for this account");
		}

		// Verify password
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}

		// Generate JWT token
		String token = jwtService.generateToken(user.getEmail());

		System.out.println("User logged in: " + user.getEmail());

		return new AuthResponse(token, user.getEmail(), user.getName(), "Login successful");
	}
}