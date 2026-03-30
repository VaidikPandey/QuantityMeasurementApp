package com.QuantityMeasurementApp.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.QuantityMeasurementApp.model.User;
import com.QuantityMeasurementApp.repository.UserRepository;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) {

		// Load user info from Google
		OAuth2User oAuth2User = super.loadUser(userRequest);

		// Extract user details from Google response
		String email = oAuth2User.getAttribute("email");
		String name = oAuth2User.getAttribute("name");
		String picture = oAuth2User.getAttribute("picture");

		// Check if user already exists in our DB
		Optional<User> existingUser = userRepository.findByEmail(email);

		if (existingUser.isEmpty()) {
			// First time login - save user to DB
			User newUser = new User(email, name, picture, "google");
			userRepository.save(newUser);
			System.out.println("New user saved: " + email);
		} else {
			System.out.println("Existing user logged in: " + email);
		}

		return oAuth2User;
	}
}