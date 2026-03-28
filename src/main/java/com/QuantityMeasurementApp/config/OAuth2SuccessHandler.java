package com.QuantityMeasurementApp.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.QuantityMeasurementApp.security.JwtService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private JwtService jwtService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// Get the logged in Google user
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

		// Extract email from Google user info
		String email = oAuth2User.getAttribute("email");
		String name = oAuth2User.getAttribute("name");

		// Generate JWT token for this user
		String token = jwtService.generateToken(email);

		// Log the successful login
		System.out.println("User logged in: " + name + " (" + email + ")");
		System.out.println("JWT Token generated: " + token);

		// Send token back in response
		response.setContentType("application/json");
		response.getWriter().write(
				"{\"token\": \"" + token + "\", " + "\"email\": \"" + email + "\", " + "\"name\": \"" + name + "\"}");
	}
}