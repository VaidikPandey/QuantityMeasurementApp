package com.QuantityMeasurementApp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.QuantityMeasurementApp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// Find user by email
	Optional<User> findByEmail(String email);

	// Check if user exists
	boolean existsByEmail(String email);
}