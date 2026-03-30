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

		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

		String email = oAuth2User.getAttribute("email");
		String name = oAuth2User.getAttribute("name");

		// generate JWT token
		String token = jwtService.generateToken(email);

		System.out.println("Google login success: " + email);

		// redirect to frontend dashboard with token in URL
		String redirectUrl = "http://localhost:5500/dashboard.html" + "?token=" + token + "&name=" + name + "&email="
				+ email;

		response.sendRedirect(redirectUrl);
	}
}