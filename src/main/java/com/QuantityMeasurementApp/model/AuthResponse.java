package com.QuantityMeasurementApp.model;

public class AuthResponse {

	private String token;
	private String email;
	private String name;
	private String message;

	public AuthResponse() {
	}

	public AuthResponse(String token, String email, String name, String message) {
		this.token = token;
		this.email = email;
		this.name = name;
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getMessage() {
		return message;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}