package com.QuantityMeasurementApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String name;

	@Column
	private String picture;

	@Column(nullable = false)
	private String provider;

	public User() {
	}

	public User(String email, String name, String picture, String provider) {
		this.email = email;
		this.name = name;
		this.picture = picture;
		this.provider = provider;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPicture() {
		return picture;
	}

	public String getProvider() {
		return provider;
	}

	@Override
	public String toString() {
		return "User[email=" + email + ", name=" + name + "]";
	}
}